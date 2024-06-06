package com.study.newforest2.core.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class BizExceptionAdvice {

    @ExceptionHandler(value = BizException.class)
    protected StandardResponse failErrorException(BizException e) {
        return StandardResponse.fail(e.getErrorCode(), e.getMessage());
    }

    @ExceptionHandler(value = Exception.class)
    protected StandardResponse commonException(Exception e) {
        log.error(e.getMessage(), e);
        return StandardResponse.fail(500, "오류가 발생했습니다.");
    }

}
