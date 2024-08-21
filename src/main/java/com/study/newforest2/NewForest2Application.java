package com.study.newforest2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
@EnableCaching
public class NewForest2Application {

    public static void main(String[] args) {
        SpringApplication.run(NewForest2Application.class, args);
    }

}
