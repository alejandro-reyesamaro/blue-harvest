package com.harvest.api.controllers.strategies;

import com.harvest.api.controllers.strategies.dto.BaseResponse;
import com.harvest.api.controllers.strategies.dto.GetCostumerInjectionsResponse;
import com.harvest.application.features.dto.GetCostumerInjectionsResult;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import static org.springframework.http.HttpStatus.OK;

@Component
public class GetCostumerInjectionsNotFoundResponseStrategy implements ICrudResponseStrategy<GetCostumerInjectionsResult> {
    
    public boolean itApplies(GetCostumerInjectionsResult result) {
        return result.isSuccess() && result.getInjections().size() == 0;
    }

    public ResponseEntity<BaseResponse> run(GetCostumerInjectionsResult result) {
        BaseResponse response = new GetCostumerInjectionsResponse(
            result.getInjections(),
            "[SUCCESS] " + result.getMessage()
        );
        return new ResponseEntity<BaseResponse>(response, OK);
    }
}
