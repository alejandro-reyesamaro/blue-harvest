package com.harvest.api.controllers.config;

import com.harvest.application.services.ICostumerService;
import com.harvest.infrastructure.services.CostumerService;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CostumerControllerConfiguration {
    
    @Bean
    public ICostumerService buildCostumerService() {
        return new CostumerService();
    }
}
