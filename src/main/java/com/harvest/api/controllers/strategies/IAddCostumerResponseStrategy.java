package com.harvest.api.controllers.strategies;

import com.harvest.api.controllers.strategies.dto.BaseResponse;
import com.harvest.application.features.dto.AddCostumerResult;

import org.springframework.http.ResponseEntity;

public interface IAddCostumerResponseStrategy {
    boolean itApplies(AddCostumerResult result);
    ResponseEntity<BaseResponse> run(AddCostumerResult result);
}
