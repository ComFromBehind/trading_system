package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.http.CacheControl;

import org.springframework.transaction.annotation.Isolation;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.transaction.Transactional;
import java.util.concurrent.TimeUnit;
import java.util.logging.Filter;

public class MvcConfiguration implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/templates/","classpath:/static")
                .setCacheControl(CacheControl.maxAge(10, TimeUnit.MINUTES));
    }

}
