package com.harvest.api.controllers.config;

import com.harvest.application.services.IAccountService;
import com.harvest.infrastructure.services.AccountService;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AccountControllerConfiguration {
    
    @Bean
    public IAccountService buildAccountService() {
        return new AccountService();
    }
}
