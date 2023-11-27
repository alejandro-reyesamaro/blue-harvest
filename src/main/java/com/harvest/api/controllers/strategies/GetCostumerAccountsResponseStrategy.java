package com.harvest.api.controllers.strategies;

import com.harvest.api.controllers.strategies.dto.BaseResponse;
import com.harvest.application.features.dto.GetCostumerAccountsResult;
import com.harvest.api.controllers.strategies.dto.GetCostumerAccountsResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import static org.springframework.http.HttpStatus.OK;

@Component
public class GetCostumerAccountsResponseStrategy implements ICrudResponseStrategy<GetCostumerAccountsResult> {
    
    public boolean itApplies(GetCostumerAccountsResult result) {
        return result.isSuccess() && result.getAccounts().size() > 0;
    }

    public ResponseEntity<BaseResponse> run(GetCostumerAccountsResult result) {
        BaseResponse response = new GetCostumerAccountsResponse(
            result.getAccounts(),
            "[SUCCESS] " + result.getMessage()
        );
        return new ResponseEntity<BaseResponse>(response, OK);
    }
}
