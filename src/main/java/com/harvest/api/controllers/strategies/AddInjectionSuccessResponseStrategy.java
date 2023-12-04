package com.harvest.api.controllers.strategies;

import com.harvest.api.controllers.strategies.dto.AddInjectionResponse;
import com.harvest.api.controllers.strategies.dto.BaseResponse;
import com.harvest.application.features.dto.AddInjectionResult;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import static org.springframework.http.HttpStatus.CREATED;

@Component
public class AddInjectionSuccessResponseStrategy implements ICrudResponseStrategy<AddInjectionResult> {
    
    public boolean itApplies(AddInjectionResult result) {
        return result.isSuccess();
    }

    public ResponseEntity<BaseResponse> run(AddInjectionResult result) {
        BaseResponse response = new AddInjectionResponse(
            Integer.toString(result.getInjection().getCostumerAccount().getId()),
            Double.toString(result.getInjection().getAmount()),
            result.getInjection().getDate().toString(),
            "[SUCCESS] " + result.getMessage()
        );
        return new ResponseEntity<BaseResponse>(response, CREATED);
    }
}
