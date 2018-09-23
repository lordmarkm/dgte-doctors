package com.ampota.card;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import xyz.xpay.shared.web.exceptionhandler.GlobalExceptionHandler;

@Configuration
public class WebConfig {

    @Bean
    public GlobalExceptionHandler globalExceptionHandler() {
        return new GlobalExceptionHandler();
    }

}
