package com.harvest.api.controllers.strategies;

import com.harvest.api.controllers.strategies.dto.BaseResponse;
import com.harvest.api.controllers.strategies.dto.GetAllCostumersResponse;
import com.harvest.application.features.dto.GetAllCostumersResult;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import static org.springframework.http.HttpStatus.OK;

@Component
public class GetAllCostumersResponseStrategy implements ICrudResponseStrategy<GetAllCostumersResult>{
    
    public boolean itApplies(GetAllCostumersResult result) {
        return result.isSuccess() && result.getCostumers().size() > 0;
    }

    public ResponseEntity<BaseResponse> run(GetAllCostumersResult result) {
        BaseResponse response = new GetAllCostumersResponse(
            result.getCostumers(),
            "[SUCCESS] " + result.getMessage()
        );
        return new ResponseEntity<BaseResponse>(response, OK);
    }
}
