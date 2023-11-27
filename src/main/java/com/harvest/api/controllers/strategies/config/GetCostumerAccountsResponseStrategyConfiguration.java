package com.harvest.api.controllers.strategies.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.harvest.api.controllers.strategies.GetCostumerAccountsResponseStrategy;
import com.harvest.api.controllers.strategies.GetCostumerNotFoundResponseStrategy;
import com.harvest.api.controllers.strategies.GetEmptyAccountListResponseStrategy;
import com.harvest.api.controllers.strategies.ICrudResponseStrategy;
import com.harvest.application.features.dto.GetCostumerAccountsResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GetCostumerAccountsResponseStrategyConfiguration {
    
    @Autowired
    protected GetCostumerAccountsResponseStrategy successStrategy;

    @Autowired
    protected GetCostumerNotFoundResponseStrategy notFoundStrategy; 

    @Autowired
    protected GetEmptyAccountListResponseStrategy emptyStrategy;

    @Bean
    public List<ICrudResponseStrategy<GetCostumerAccountsResult>> buildGetCostumerAccountsResponseStrategyList() {
        return new ArrayList<ICrudResponseStrategy<GetCostumerAccountsResult>>(
            Arrays.asList(
                successStrategy,
                notFoundStrategy,
                emptyStrategy
        ));
    }
}
