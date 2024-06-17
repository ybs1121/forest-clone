package com.study.newforest2.core.common;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;

@Aspect
@Component
@Slf4j
public class RequestLoggingAspect {

    @Around("execution(* com.study.newforest2.biz.controller.*.*(..))")
    public Object loggingDetails(ProceedingJoinPoint joinPoint) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String uri = request.getRequestURI();
        String method = request.getMethod();
        String requestBody = getRequestBody(request);

        log.info("Request: {} {}, Body: {}", method, uri, requestBody);

        Object result = joinPoint.proceed();

        return result;
    }

    private String getRequestBody(HttpServletRequest request) {
        try {
            StringBuilder stringBuilder = new StringBuilder();
            byte[] buffer = new byte[1024];
            int bytesRead;
            try (InputStream inputStream = request.getInputStream()) {
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    stringBuilder.append(new String(buffer, 0, bytesRead));
                }
            }
            return stringBuilder.toString();
        } catch (IOException e) {
            log.error("Error reading request body", e);
            return "Error reading request body";
        }
    }
}
