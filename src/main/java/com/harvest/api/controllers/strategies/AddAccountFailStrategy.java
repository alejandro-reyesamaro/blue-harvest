package com.harvest.api.controllers.strategies;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

import com.harvest.api.controllers.strategies.dto.AddAccountFailResponse;
import com.harvest.api.controllers.strategies.dto.AddAccountResponse;
import com.harvest.application.features.dto.AddAccountResult;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class AddAccountFailStrategy implements IAddAccountResponseStrategy {
    
    public boolean itApplies(AddAccountResult response) {
        return !response.isSuccess();
    }

    public ResponseEntity<AddAccountResponse> Run(AddAccountResult result) {
        AddAccountResponse response = new AddAccountFailResponse(
            "[FAIL] " + result.getMessage()
        );
        return new ResponseEntity<AddAccountResponse>(response, BAD_REQUEST);
    }
}