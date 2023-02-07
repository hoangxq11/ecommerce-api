package com.demo.web.exception.handle;

import com.demo.web.dto.response.utils.ErrorResponse;
import com.demo.web.dto.response.utils.Response;
import com.demo.web.dto.response.utils.ResponseUtils;
import com.demo.web.exception.AuthenticateException;
import com.demo.web.exception.UsernameOrEmailTakenException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@Slf4j
@ControllerAdvice
@RequiredArgsConstructor
public class ExceptionControllerAdvice {

    @ExceptionHandler({Exception.class})
    public ResponseEntity<Response> unknownErrorHandler(Exception e) {
        log.error("Unexpected Exception", e);
        String errMsg = "Unknown Error";
        return ResponseUtils.internalErr(
                ErrorResponse.of(ServiceError.UNEXPECTED_EXCEPTION.getMessageKey(), errMsg));
    }

    @ExceptionHandler({AuthenticateException.class})
    public ResponseEntity<Response> authenticateErrorHandler(Exception e) {
        log.error(e.getMessage());
        String errMsg = "Username or password is invalid";
        return ResponseUtils.unauthorized(
                ErrorResponse.of(ServiceError.INVALID_USERNAME_OR_PASSWORD.getMessageKey(), errMsg)
        );
    }

    @ExceptionHandler({UsernameOrEmailTakenException.class})
    public ResponseEntity<Response> usernameOrEmailTakenError(Exception e) {
        log.error(e.getMessage());
        String errMsg = "Username or password is already taken!";
        return ResponseUtils.unauthorized(
                ErrorResponse.of(ServiceError.USERNAME_OR_PASSWORD_TAKEN.getMessageKey(), errMsg)
        );
    }

}
