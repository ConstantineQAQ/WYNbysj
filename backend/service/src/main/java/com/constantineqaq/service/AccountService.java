package com.constantineqaq.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.constantineqaq.dal.mapper.AccountMapper;
import com.constantineqaq.base.entity.Account;
import com.constantineqaq.base.entity.LoginUser;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
public class AccountService implements UserDetailsService {
    @Resource
    private AccountMapper accountMapper;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if(StringUtils.isBlank(username)){
            throw new RuntimeException("请输入用户名");
        }
        QueryWrapper<Account> wrapper = new QueryWrapper<>();
        wrapper.eq("user_name",username).last("limit 1");
        Account user = accountMapper.selectOne(wrapper);
        if(Objects.isNull(user)){
            throw new RuntimeException("用户名不存在");
        }

        //传递用户所具有的权限
        List<String> list = new ArrayList<>(Arrays.asList("test", "admin"));
        return new LoginUser(user,list);

    }
}
