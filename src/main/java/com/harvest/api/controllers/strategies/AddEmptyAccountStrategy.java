package com.harvest.api.controllers.strategies;

import com.harvest.api.controllers.strategies.dto.AddAccountResponse;
import com.harvest.api.controllers.strategies.dto.AddEmptyAccountResponse;
import com.harvest.application.features.dto.AddAccountResult;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import static org.springframework.http.HttpStatus.CREATED;

@Component
public class AddEmptyAccountStrategy implements IAddAccountResponseStrategy {
    
    public boolean itApplies(AddAccountResult result) {
        return result.isSuccess() && result.getInjection() == null;
    }

    public ResponseEntity<AddAccountResponse> Run(AddAccountResult result) {
        AddAccountResponse response = new AddEmptyAccountResponse(
            result.getNewAccount().getName(),
            "[SUCCESS] " + result.getMessage()
        );
        return new ResponseEntity<AddAccountResponse>(response, CREATED);
    }
}
