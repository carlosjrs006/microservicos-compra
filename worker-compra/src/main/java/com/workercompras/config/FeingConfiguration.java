package com.workercompras.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class FeingConfiguration {

    @Bean
    public Logger.Level feignLoggerLevel(){
        return Logger.Level.HEADERS;
    }
}
