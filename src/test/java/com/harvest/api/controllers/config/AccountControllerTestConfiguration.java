package com.harvest.api.controllers.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.harvest.api.controllers.strategies.AddAccountFailResponseStrategy;
import com.harvest.api.controllers.strategies.AddEmptyAccountResponseStrategy;
import com.harvest.api.controllers.strategies.AddFundedAccountResponseStrategy;
import com.harvest.api.controllers.strategies.GetCostumerAccountsResponseStrategy;
import com.harvest.api.controllers.strategies.GetCostumerNotFoundResponseStrategy;
import com.harvest.api.controllers.strategies.GetEmptyAccountListResponseStrategy;
import com.harvest.api.controllers.strategies.ICrudResponseStrategy;
import com.harvest.application.features.dto.AddAccountResult;
import com.harvest.application.features.dto.GetCostumerAccountsResult;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class AccountControllerTestConfiguration {
    
    @Bean
    public List<ICrudResponseStrategy<AddAccountResult>> buildAddAccountResponseStrategyListForTest() {
        return new ArrayList<ICrudResponseStrategy<AddAccountResult>>(
            Arrays.asList(
                new AddAccountFailResponseStrategy(),
                new AddFundedAccountResponseStrategy(),
                new AddEmptyAccountResponseStrategy()
        ));
    }

    @Bean
    protected List<ICrudResponseStrategy<GetCostumerAccountsResult>> buildCostumerAccountStrategiesForTest() {
        return new ArrayList<ICrudResponseStrategy<GetCostumerAccountsResult>>(
            Arrays.asList(
                new GetCostumerNotFoundResponseStrategy(),
                new GetEmptyAccountListResponseStrategy(),
                new GetCostumerAccountsResponseStrategy()
        ));
    }
}
