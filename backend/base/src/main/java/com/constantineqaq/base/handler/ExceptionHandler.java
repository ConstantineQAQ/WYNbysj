package com.constantineqaq.base.handler;

import com.constantineqaq.base.exception.ResourceNotFoundException;
import com.constantineqaq.base.response.HttpResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.ServletException;

/**
 * @author wangyaning33
 */
@RestController
@ControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    public HttpResponse<String> error(Exception e) {
        if(e instanceof NoHandlerFoundException exception) {
            return HttpResponse.failure(404, e.getMessage());
        } else if (e instanceof ServletException exception) {
            return HttpResponse.failure(400, e.getMessage());
        } else if (e instanceof ResourceNotFoundException exception) {
            return HttpResponse.failure(404, e.getMessage());
        } else if (e instanceof AuthenticationException exception) {
            return HttpResponse.failure(401, e.getMessage());
        } else if (e instanceof AccessDeniedException exception) {
            return HttpResponse.failure(403, e.getMessage());
        } else {
            return HttpResponse.failure(500, e.getMessage());
        }
    }
}
