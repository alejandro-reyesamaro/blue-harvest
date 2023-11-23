package com.harvest.api.controllers.config;

import com.harvest.application.services.ITransactionService;
import com.harvest.infrastructure.services.TransactionService;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TransactionControllerConfiguration {
    
    @Bean
    public ITransactionService buildTransactionService() {
        return new TransactionService();
    }
}
