package com.example.javaspringlessons.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.File;

@EnableWebMvc
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String dirPath = System.getProperty("user.dir") + File.separator + "images" + File.separator;
        registry.addResourceHandler("/img/**")
                .addResourceLocations("file:///" + dirPath);
    }
}
