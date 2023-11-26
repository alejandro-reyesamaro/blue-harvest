package com.harvest.api.controllers.strategies;

import com.harvest.api.controllers.strategies.dto.BaseResponse;
import com.harvest.application.features.dto.GetCostumerAccountsResult;

import org.springframework.http.ResponseEntity;

public interface IGetCostumerAccountsResponseStrategy {
    boolean itApplies(GetCostumerAccountsResult result);
    ResponseEntity<BaseResponse> run(GetCostumerAccountsResult result);
}
