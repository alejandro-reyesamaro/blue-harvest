package com.harvest.api.controllers.strategies;

import com.harvest.application.features.dto.GetCostumerAccountsResult;

import org.springframework.stereotype.Component;

@Component
public class GetCostumerNotFoundResponseStrategy extends FailResponseStrategy<GetCostumerAccountsResult> {}
