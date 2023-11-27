package com.harvest.api.controllers.strategies;

import org.springframework.stereotype.Component;

import com.harvest.application.features.dto.GetCostumerInjectionsResult;

@Component
public class GetCostumerInjectionsCostumerNotFoundResponseStrategy extends FailResponseStrategy<GetCostumerInjectionsResult> {}