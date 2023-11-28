package com.harvest.api.controllers.strategies;

import com.harvest.api.controllers.strategies.dto.AddTransactionResponse;
import com.harvest.api.controllers.strategies.dto.BaseResponse;
import com.harvest.application.features.dto.AddTransactionResult;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import static org.springframework.http.HttpStatus.CREATED;

@Component

public class AddTransactionSuccessResponseStrategy implements ICrudResponseStrategy<AddTransactionResult> {
    
    public boolean itApplies(AddTransactionResult result) {
        return result.isSuccess();
    }

    public ResponseEntity<BaseResponse> run(AddTransactionResult result) {
        BaseResponse response = new AddTransactionResponse(
            Integer.toString(result.getTransaction().getCostumerAccountId()),
            Integer.toString(result.getTransaction().getTargetAccountId()),
            Double.toString(result.getTransaction().getAmount()),
            result.getTransaction().getDate().toString(),
            "[SUCCESS] " + result.getMessage()
        );
        return new ResponseEntity<BaseResponse>(response, CREATED);
    }
}