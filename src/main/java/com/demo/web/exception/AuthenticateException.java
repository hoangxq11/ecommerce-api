package com.demo.web.exception;

import com.demo.web.exception.handle.ServiceError;

public class AuthenticateException extends RuntimeException{
    public AuthenticateException(){
        super(ServiceError.INVALID_USERNAME_OR_PASSWORD.getMessageKey(), null);
    }
}
