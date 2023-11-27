package com.harvest.api.controllers.strategies.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.harvest.api.controllers.strategies.GetCostumerAccountsStrategy;
import com.harvest.api.controllers.strategies.GetCostumerNotFoundStrategy;
import com.harvest.api.controllers.strategies.GetEmptyAccountListStrategy;
import com.harvest.api.controllers.strategies.IGetCostumerAccountsResponseStrategy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GetCostumerAccountsResponseStrategyConfiguration {
    
    @Autowired
    protected GetCostumerAccountsStrategy successStrategy;

    @Autowired
    protected GetCostumerNotFoundStrategy notFoundStrategy; 

    @Autowired
    protected GetEmptyAccountListStrategy emptyStrategy;

    @Bean
    public List<IGetCostumerAccountsResponseStrategy> buildGetCostumerAccountsResponseStrategyList() {
        return new ArrayList<IGetCostumerAccountsResponseStrategy>(
            Arrays.asList(
                successStrategy,
                notFoundStrategy,
                emptyStrategy
        ));
    }
}
