package com.harvest.api.controllers.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.harvest.api.controllers.strategies.AddCostumerFailResponseStrategy;
import com.harvest.api.controllers.strategies.AddCostumerSuccessResponseStrategy;
import com.harvest.api.controllers.strategies.GetAllCostumersEmptyResponseStrategy;
import com.harvest.api.controllers.strategies.GetAllCostumersResponseStrategy;
import com.harvest.api.controllers.strategies.ICrudResponseStrategy;
import com.harvest.application.features.dto.AddCostumerResult;
import com.harvest.application.features.dto.GetAllCostumersResult;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class CostumerControllerTestConfiguration {

    @Bean
    public List<ICrudResponseStrategy<GetAllCostumersResult>> buildGetAllCostumersResponseStrategyList4test() {
        return new ArrayList<ICrudResponseStrategy<GetAllCostumersResult>>(
            Arrays.asList(
                new GetAllCostumersResponseStrategy(),
                new GetAllCostumersEmptyResponseStrategy()
        ));
    }

    @Bean
    public List<ICrudResponseStrategy<AddCostumerResult>> buildAddCostumerResponseStrategyList4test() {
        return new ArrayList<ICrudResponseStrategy<AddCostumerResult>>(
            Arrays.asList(
                new AddCostumerFailResponseStrategy(),
                new AddCostumerSuccessResponseStrategy()
        ));
    }
}
