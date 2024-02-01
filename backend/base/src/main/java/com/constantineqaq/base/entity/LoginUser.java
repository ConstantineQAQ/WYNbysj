package com.constantineqaq.base.entity;


import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@NoArgsConstructor
public class LoginUser implements UserDetails {

    private Account user;

    private List<String> permission;

    public LoginUser(Account user,List<String> permission) {
        this.user = user;
        this.permission=permission;
    }

    @JSONField(serialize = false)
    private List<GrantedAuthority>  authorities;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        authorities = new ArrayList<>();
        // 注意 为什么这里不直接返回注意，
        // 之所以不直接传递authorities，而是传递permission，在getAuthorities 时动态的使用permission封装authorities，
        // 那是因为redis对于 List<GrantedAuthority>没法序列化。反序列化时会报错。
        for (String perm : permission) {
            authorities.add(new SimpleGrantedAuthority(perm));
        }
        return authorities; //该用户有哪些权限
    }

    @Override
    public String getPassword() {
        return this.user.getPassword();
    }

    @Override
    public String getUsername() {
        return this.user.getUserName();
    }

    @Override
    public boolean isAccountNonExpired() {//帐号是不是没有过期
        return true;
    }

    @Override
    public boolean isAccountNonLocked() { //是不是没有被锁定
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() { //凭证是不是没有过期
        return true;
    }

    @Override
    public boolean isEnabled() {  //是否可用
        return true;
    }
}
