package com.harvest.api.controllers.strategies;

import com.harvest.api.controllers.strategies.dto.AddAccountResponse;
import com.harvest.application.features.dto.AddAccountResult;

import org.springframework.http.ResponseEntity;

public interface IAddAccountResponseStrategy {
    boolean itApplies(AddAccountResult result);
    ResponseEntity<AddAccountResponse> Run(AddAccountResult result);
}
