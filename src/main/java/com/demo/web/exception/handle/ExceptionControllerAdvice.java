package com.demo.web.exception.handle;

import com.demo.web.dto.response.utils.ErrorResponse;
import com.demo.web.dto.response.utils.Response;
import com.demo.web.dto.response.utils.ResponseUtils;
import com.demo.web.exception.ServiceException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
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
        String errKey = "err.sys.unexpected-exception";
        String errMsg = "Unknown error";
        return ResponseUtils.internalErr(ErrorResponse.of(errKey, errMsg));
    }

    @ExceptionHandler({ServiceException.class})
    public ResponseEntity<Response> ServiceErrorHandler(ServiceException e) {
        String errKey = e.getMessage();
        String errMsg = e.getErrMsg();
        log.error(errKey, errMsg);
        return ResponseUtils.badRequest(ErrorResponse.of(errKey, errMsg));
    }

}
