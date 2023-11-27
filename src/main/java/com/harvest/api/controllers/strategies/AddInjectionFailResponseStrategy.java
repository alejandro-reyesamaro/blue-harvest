package com.harvest.api.controllers.strategies;

import com.harvest.api.controllers.strategies.dto.BaseResponse;
import com.harvest.application.features.dto.AddInjectionResult;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Component
public class AddInjectionFailResponseStrategy implements ICrudResponseStrategy<AddInjectionResult> {

    public boolean itApplies(AddInjectionResult result) {
        return !result.isSuccess();
    }

    public ResponseEntity<BaseResponse> run(AddInjectionResult result) {
        BaseResponse response = new BaseResponse(
            "[FAIL] " + result.getMessage()
        );
        return new ResponseEntity<BaseResponse>(response, BAD_REQUEST);
    }    
}
