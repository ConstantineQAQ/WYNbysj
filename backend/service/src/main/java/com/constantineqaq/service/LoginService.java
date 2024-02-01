package com.constantineqaq.service;

import com.constantineqaq.base.response.HttpResponse;
import com.constantineqaq.base.entity.Account;

public interface LoginService {
    public HttpResponse login(Account user);

    public HttpResponse logout();
}
