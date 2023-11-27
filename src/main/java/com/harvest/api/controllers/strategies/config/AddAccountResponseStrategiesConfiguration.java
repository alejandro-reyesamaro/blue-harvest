package com.harvest.api.controllers.strategies.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.harvest.api.controllers.strategies.AddAccountFailStrategy;
import com.harvest.api.controllers.strategies.AddEmptyAccountStrategy;
import com.harvest.api.controllers.strategies.AddFundedAccountStrategy;
import com.harvest.api.controllers.strategies.ICrudStrategy;
import com.harvest.application.features.dto.AddAccountResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AddAccountResponseStrategiesConfiguration {
    
    @Autowired
    protected AddAccountFailStrategy failStrategy;

    @Autowired 
    protected AddFundedAccountStrategy fundedStrategy;

    @Autowired
    protected AddEmptyAccountStrategy emptyStrategy;

    @Bean
    public List<ICrudStrategy<AddAccountResult>> buildAddAccountResponseStrategyList() {
        return new ArrayList<ICrudStrategy<AddAccountResult>>(
            Arrays.asList(
                failStrategy,
                fundedStrategy,
                emptyStrategy
        ));
    }
}
