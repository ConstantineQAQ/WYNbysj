package com.constantineqaq.web.controller;

import com.constantineqaq.base.response.HttpResponse;
import com.constantineqaq.base.entity.Account;
import com.constantineqaq.service.LoginService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/user")
public class LoginController {

    @Resource
    private LoginService loginService;

    @PostMapping("/login")
    public HttpResponse login(@RequestBody Account user){
        return loginService.login(user);
    }

    @PostMapping("/logout")
    public HttpResponse logout(){
        return loginService.logout();
    }
}