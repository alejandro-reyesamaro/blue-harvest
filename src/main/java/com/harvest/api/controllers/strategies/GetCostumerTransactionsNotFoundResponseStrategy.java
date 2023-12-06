package com.harvest.api.controllers.strategies;

import com.harvest.api.controllers.strategies.dto.BaseResponse;
import com.harvest.api.controllers.strategies.dto.GetCostumerTransactionsResponse;
import com.harvest.application.features.dto.GetCostumerTransactionsResult;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import static org.springframework.http.HttpStatus.OK;

@Component
public class GetCostumerTransactionsNotFoundResponseStrategy implements ICrudResponseStrategy<GetCostumerTransactionsResult> {
    
    public boolean itApplies(GetCostumerTransactionsResult result) {
        return result.isSuccess() && result.getTransactions().size() == 0;
    }

    public ResponseEntity<BaseResponse> run(GetCostumerTransactionsResult result) {
        BaseResponse response = new GetCostumerTransactionsResponse(
            result.getCostumer(),
            result.getTransactions(),
            "[SUCCESS] " + result.getMessage()
        );
        return new ResponseEntity<BaseResponse>(response, OK);
    }
}
