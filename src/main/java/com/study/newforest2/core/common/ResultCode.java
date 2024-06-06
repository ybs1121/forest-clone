package com.study.newforest2.core.common;

public enum ResultCode {

    RESULT_OK("OK", "성공하였습니다."),
    ;

    private String resultCode;
    private String message;


    ResultCode(String resultCode, String message) {
        this.resultCode = resultCode;
        this.message = message;
    }


}
