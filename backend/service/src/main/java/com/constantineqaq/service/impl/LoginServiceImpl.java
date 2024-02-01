package com.constantineqaq.service.impl;

import com.constantineqaq.base.response.HttpResponse;
import com.constantineqaq.base.utils.JwtUtils;
import com.constantineqaq.base.utils.RedisUtils;
import com.constantineqaq.base.entity.Account;
import com.constantineqaq.base.entity.LoginUser;
import com.constantineqaq.service.LoginService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class LoginServiceImpl implements LoginService {
    @Resource
    private AuthenticationManager authenticationManager;

    @Resource
    private RedisUtils redisUtils;

    @Override
    public HttpResponse login(Account user) {

        //使用Authentication的实现类
        Authentication authentication = new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword());

        //手动调用方法去认证 他会自动调用UserDetailsService查 然后对比啥的
        Authentication authenticate = authenticationManager.authenticate(authentication);
        if(Objects.isNull(authenticate)){ //说明输入错误
            throw new  RuntimeException("用户名或密码错误");
        }
        //拿到用户信息 然后生成jwt返回给前端，并且将用户的信息存入redis
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal(); // 这个其实就是UserDetails 也就是LoginUser
        String userId = loginUser.getUser().getId().toString();


        String jwt = JwtUtils.createJWT(userId);
        redisUtils.set("Login:"+userId,loginUser);

        Map<String, String> map = new HashMap<>();
        map.put("token",jwt);
        return new HttpResponse(200,map,"登录成功");
    }

    @Override
    public HttpResponse logout() {
        //因为这个方法 是通过了jwt过滤器执行到这里的 所以SecurityContextHolder上下文是一样的
        LoginUser loginUser = (LoginUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        //拿到用户id删除redis中的数据
        String userId  = loginUser.getUser().getId().toString();
        redisUtils.del("Login:"+userId);
        return new HttpResponse(200,null,"退出成功");
    }
}
