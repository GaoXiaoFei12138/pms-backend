package com.example.personnel.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // 允许所有路径
                .allowedOriginPatterns("*") // 允许所有来源
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // 允许所有请求方法
                .allowedHeaders("*") // 允许所有请求头
                .allowCredentials(true); // 允许携带凭证
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 添加新的资源处理器，用于处理 /uploads/images/** 请求
        // 使用相对路径映射静态资源
        registry.addResourceHandler("/uploads/images/**")
                .addResourceLocations("file:" + System.getProperty("user.dir") + "/uploads/images/",
                        "classpath:/static/uploads/images/");
    }
}