package com.study.newforest2.biz.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    private ApplicationContext context;

    @GetMapping("/testRedisTemplate")
    public String testRedisTemplate() {
        boolean hasBean = context.containsBean("redisTemplate");
        return hasBean ? "RedisTemplate bean is available" : "RedisTemplate bean is not available";
    }
}