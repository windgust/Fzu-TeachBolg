package com.fzuTeachBolg.ask;

import com.fzuTeachBolg.core.UKDataContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.web.context.WebApplicationContext;

public class SpringBootStartApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        System.out.println("*******************************************");
        System.out.println("SpringApplicationBuilder");
        System.out.println("*******************************************");
        return builder.sources(Application.class);
    }

    @Override
    protected WebApplicationContext run(SpringApplication application) {
        System.out.println("*******************************************");
        System.out.println("WebApplicationContext");
        System.out.println("*******************************************");
        WebApplicationContext webApplicationContext = super.run(application);
        UKDataContext.setApplicationContext(webApplicationContext);
        return webApplicationContext;
    }
}
