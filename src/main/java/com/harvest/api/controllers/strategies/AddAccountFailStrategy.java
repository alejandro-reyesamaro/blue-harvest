package com.harvest.api.controllers.strategies;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

import com.harvest.api.controllers.strategies.dto.BaseResponse;
import com.harvest.application.features.dto.AddAccountResult;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class AddAccountFailStrategy implements IAddAccountResponseStrategy {
    
    public boolean itApplies(AddAccountResult response) {
        return !response.isSuccess();
    }

    public ResponseEntity<BaseResponse> run(AddAccountResult result) {
        BaseResponse response = new BaseResponse(
            "[FAIL] " + result.getMessage()
        );
        return new ResponseEntity<BaseResponse>(response, BAD_REQUEST);
    }
}