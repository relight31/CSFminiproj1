package com.vttp.miniprojserver.configurations;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class AppConfig {
    @Value("${cors.pathMapping}")
    private String path;

    @Value("${cors.origins}")
    private String origins;

    @Bean
    public WebMvcConfigurer webMvcConfigurer() {
        return new CORSConfiguration(path, origins);
    }
}
