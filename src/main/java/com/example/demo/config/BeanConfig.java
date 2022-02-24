package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

@Configuration
public class BeanConfig {
    @Bean
    public DateFormat dateFormat(){
        return new SimpleDateFormat("yyyy-MM-dd");
    }
}
