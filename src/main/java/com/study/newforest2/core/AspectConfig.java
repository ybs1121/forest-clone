package com.study.newforest2.core;

import com.study.newforest2.core.common.RequestLoggingAspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
public class AspectConfig {
    @Bean
    public RequestLoggingAspect requestLoggingAspect() {
        return new RequestLoggingAspect();
    }
}
