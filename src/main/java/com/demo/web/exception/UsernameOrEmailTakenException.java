package com.demo.web.exception;

import com.demo.web.exception.handle.ServiceError;

public class UsernameOrEmailTakenException extends RuntimeException{
    public UsernameOrEmailTakenException(){
        super(ServiceError.USERNAME_OR_PASSWORD_TAKEN.getMessageKey(), null);
    }
}
