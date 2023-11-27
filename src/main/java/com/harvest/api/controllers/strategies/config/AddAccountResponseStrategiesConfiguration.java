package com.harvest.api.controllers.strategies.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.harvest.api.controllers.strategies.AddAccountFailResponseStrategy;
import com.harvest.api.controllers.strategies.AddEmptyAccountResponseStrategy;
import com.harvest.api.controllers.strategies.AddFundedAccountResponseStrategy;
import com.harvest.api.controllers.strategies.ICrudResponseStrategy;
import com.harvest.application.features.dto.AddAccountResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AddAccountResponseStrategiesConfiguration {
    
    @Autowired
    protected AddAccountFailResponseStrategy failStrategy;

    @Autowired 
    protected AddFundedAccountResponseStrategy fundedStrategy;

    @Autowired
    protected AddEmptyAccountResponseStrategy emptyStrategy;

    @Bean
    public List<ICrudResponseStrategy<AddAccountResult>> buildAddAccountResponseStrategyList() {
        return new ArrayList<ICrudResponseStrategy<AddAccountResult>>(
            Arrays.asList(
                failStrategy,
                fundedStrategy,
                emptyStrategy
        ));
    }
}
