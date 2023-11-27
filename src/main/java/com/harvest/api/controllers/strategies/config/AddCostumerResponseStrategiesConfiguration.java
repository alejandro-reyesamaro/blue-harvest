package com.harvest.api.controllers.strategies.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.harvest.api.controllers.strategies.AddCostumerFailResponseStrategy;
import com.harvest.api.controllers.strategies.AddCostumerSuccessResponseStrategy;
import com.harvest.api.controllers.strategies.ICrudResponseStrategy;
import com.harvest.application.features.dto.AddCostumerResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AddCostumerResponseStrategiesConfiguration {
    
    @Autowired
    protected AddCostumerFailResponseStrategy failStrategy;

    @Autowired
    protected AddCostumerSuccessResponseStrategy successStrategy;

    @Bean
    public List<ICrudResponseStrategy<AddCostumerResult>> buildAddCostumerResponseStrategyList() {
        return new ArrayList<ICrudResponseStrategy<AddCostumerResult>>(
            Arrays.asList(
                failStrategy,
                successStrategy
        ));
    }
}
