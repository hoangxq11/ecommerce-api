package com.demo.web.exception.handle;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Getter
public enum ServiceError {
    USERNAME_OR_PASSWORD_TAKEN(401, "err.authorize.username-or-password-taken"),
    INVALID_USERNAME_OR_PASSWORD(401, "err.authorize.invalid-username-or-password"),

    ENTITY_NOT_FOUND(404, "err.api.entity-not-found"),
    UNEXPECTED_EXCEPTION(500, "err.sys.unexpected-exception");

    ServiceError(int errCode, String messageKey) {
        this.errCode = errCode;
        this.messageKey = messageKey;
    }

    private final int errCode;
    private final String messageKey;

}
