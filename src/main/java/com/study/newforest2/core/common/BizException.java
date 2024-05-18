package com.study.newforest2.core.common;


import lombok.Getter;

@Getter
public class BizException extends RuntimeException {

    private int errorCode;


    public BizException() {
        super();
    }

    public BizException(int errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }
}
