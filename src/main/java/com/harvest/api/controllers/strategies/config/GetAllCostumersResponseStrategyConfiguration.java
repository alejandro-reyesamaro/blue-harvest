package com.harvest.api.controllers.strategies.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.harvest.api.controllers.strategies.GetAllCostumersEmptyResponseStrategy;
import com.harvest.api.controllers.strategies.GetAllCostumersResponseStrategy;
import com.harvest.api.controllers.strategies.ICrudResponseStrategy;
import com.harvest.application.features.dto.GetAllCostumersResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GetAllCostumersResponseStrategyConfiguration {
    
    @Autowired
    protected GetAllCostumersResponseStrategy successStrategy;

    @Autowired
    protected GetAllCostumersEmptyResponseStrategy emptyStrategy;

    @Bean
    public List<ICrudResponseStrategy<GetAllCostumersResult>> buildGetAllCostumersResponseStrategyList() {
        return new ArrayList<ICrudResponseStrategy<GetAllCostumersResult>>(
            Arrays.asList(
                successStrategy,
                emptyStrategy
        ));
    }
}
