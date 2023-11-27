package com.harvest.api.controllers.strategies;

import com.harvest.api.controllers.strategies.dto.BaseResponse;
import com.harvest.application.features.dto.AddAccountResult;

import org.springframework.http.ResponseEntity;

public interface IAddAccountResponseStrategy {
    boolean itApplies(AddAccountResult result);
    ResponseEntity<BaseResponse> run(AddAccountResult result);
}
