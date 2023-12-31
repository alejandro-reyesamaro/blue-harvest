package com.harvest.api;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class HarvestApiConfiguration {
    
    @Value("${cors.white-list}")
    protected String corsWhiteList;

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/costumer/**").allowedOrigins(corsWhiteList);
				registry.addMapping("/account/**").allowedOrigins(corsWhiteList);
				registry.addMapping("/transaction/**").allowedOrigins(corsWhiteList);
            }
        };
    }
}
