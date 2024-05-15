package com.study.newforest2.core.common;

import lombok.Getter;

@Getter
public class StandardResponse<T> {

    private int code;
    private String msg;
    private T data;

    public static <T> StandardResponse<T> success(T data) {
        StandardResponse<T> response = new StandardResponse<>();
        response.code = 200;
        response.msg = "success";
        response.data = data;
        return response;
    }

    public static <T> StandardResponse<T> fail(T data) {
        StandardResponse<T> response = new StandardResponse<>();
        response.code = 400;
        response.msg = "fail";
//        response.data = data;
        return response;
    }
}
