package com.harvest.api.controllers.strategies;

import com.harvest.api.controllers.strategies.dto.AddCostumerSuccessResponse;
import com.harvest.api.controllers.strategies.dto.BaseResponse;
import com.harvest.application.features.dto.AddCostumerResult;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import static org.springframework.http.HttpStatus.CREATED;

@Component
public class AddCostumerSuccessResponseStrategy implements ICrudResponseStrategy<AddCostumerResult> {

    public boolean itApplies(AddCostumerResult result) {
        return result.isSuccess();
    }

    public ResponseEntity<BaseResponse> run(AddCostumerResult result){
        BaseResponse response = new AddCostumerSuccessResponse(result.getCostumer().getName(), "[SUCCESS] " + result.getMessage());
        return new ResponseEntity<BaseResponse>(response, CREATED);
    }
}
