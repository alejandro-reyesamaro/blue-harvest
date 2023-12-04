package com.harvest.api.controllers.strategies;

import com.harvest.api.controllers.strategies.dto.BaseResponse;
import com.harvest.application.features.dto.FeatureResult;

import org.springframework.http.ResponseEntity;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

public abstract class FailResponseStrategy<T extends FeatureResult> implements ICrudResponseStrategy<T> {
    
    public boolean itApplies(T result) {
        return !result.isSuccess();
    }

    public ResponseEntity<BaseResponse> run(T result) {
        BaseResponse response = new BaseResponse(
            "[FAIL] " + result.getMessage()
        );
        return new ResponseEntity<BaseResponse>(response, BAD_REQUEST);
    }
}
