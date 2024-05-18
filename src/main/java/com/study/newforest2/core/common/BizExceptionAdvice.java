package com.study.newforest2.core.common;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class BizExceptionAdvice {

    @ExceptionHandler(value = BizException.class)
    protected StandardResponse failErrorException(BizException e) {
        return StandardResponse.fail(e.getErrorCode(), e.getMessage());
    }
}
