package com.constantineqaq.base.filter;

import com.constantineqaq.base.entity.LoginUser;
import com.constantineqaq.base.utils.JwtUtils;
import com.constantineqaq.base.utils.RedisUtils;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@Component
@Slf4j
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Resource
    private RedisUtils redisUtils;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //我们先拿到请求头中的token
        //首先从Header中取出JWT
        String authorization = request.getHeader("Authorization");
        //判断是否包含JWT且格式正确
        if (authorization != null && authorization.startsWith("Bearer ")) {
            String token = authorization.substring(7);
            if (StringUtils.isBlank(token)) {
                //说明没有携带token 那么直接放行 之后的过滤器肯定会报错，那么就说明用户没有登录
                filterChain.doFilter(request, response);
                return;
            }
            //解析token
            String userid;
            try {
                Claims claims = JwtUtils.parseJWT(token);
                userid = claims.getSubject();
            } catch (Exception e) {
                log.error("解析token失败", e);
                //就说明token失效了 或者是token无效
                throw new RuntimeException("token无效");
            }
            //从redis中拿到用户的信息，给SecurityContextHolder设置上下文
            LoginUser loginUser = (LoginUser) redisUtils.get("Login:" + userid);
            if (Objects.isNull(loginUser)) {
                throw new RuntimeException("用户未登录");
            }
            //存入SecurityContextHolder上下文当中  注意 这里必须得使用三个参数的authentication
            //第三个参数为授权 也就是用户是啥身份 先不管
            Authentication authentication = new UsernamePasswordAuthenticationToken(loginUser, null, loginUser.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            //放行
            filterChain.doFilter(request, response); //那么就正常的请求接口去啦！！！
        }
    }
}
