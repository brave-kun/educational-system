package com.example.educationalsystem.config;

import com.example.educationalsystem.interceptor.JWTInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 功能：
 * 作者： bravekun
 * 日期： 2023/11/9 16:01
 */


@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new JWTInterceptor())
                .addPathPatterns("/**")     //拦截的路径
                .excludePathPatterns("/login")
                .excludePathPatterns("/test");
                //.excludePathPatterns("/**");//放行的路径
    }

}