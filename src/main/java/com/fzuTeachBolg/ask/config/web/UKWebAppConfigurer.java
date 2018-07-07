package com.fzuTeachBolg.ask.config.web;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.fzuTeachBolg.ask.web.interceptor.UserInterceptorHandler;

@Configuration
public class UKWebAppConfigurer 
        extends WebMvcConfigurerAdapter {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 多个拦截器组成一个拦截器链
        // addPathPatterns 用于添加拦截规则
        // excludePathPatterns 用户排除拦截
        registry.addInterceptor(new UserInterceptorHandler()).addPathPatterns("/**").excludePathPatterns("/login.html").excludePathPatterns("/im/**");
        super.addInterceptors(registry);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/ask/**").addResourceLocations("classpath:/templates/");
//        registry.addResourceHandler("/css").addResourceLocations("classpath:/static/");
//        registry.addResourceHandler("/js").addResourceLocations("classpath:/static/");
//        registry.addResourceHandler("/lay").addResourceLocations("classpath:/static/");
//        registry.addResourceHandler("/images").addResourceLocations("classpath:/static/images");
//        registry.addResourceHandler("/res").addResourceLocations("classpath:/static/");
    }
}