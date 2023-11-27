package com.harvest.api.controllers.strategies;

import com.harvest.api.controllers.strategies.dto.BaseResponse;
import com.harvest.application.features.dto.AddCostumerResult;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Component
public class AddCostumerFailResponseStrategy implements ICrudResponseStrategy<AddCostumerResult> {

    public boolean itApplies(AddCostumerResult result) {
        return !result.isSuccess();
    }

    public ResponseEntity<BaseResponse> run(AddCostumerResult result){
        BaseResponse response = new BaseResponse("[FAIL] " + result.getMessage());
        return new ResponseEntity<BaseResponse>(response, BAD_REQUEST);
    }
}