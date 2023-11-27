package com.harvest.api.controllers.strategies;

import com.harvest.api.controllers.strategies.dto.BaseResponse;
import com.harvest.application.features.dto.FeatureResult;

import org.springframework.http.ResponseEntity;

public interface ICrudStrategy<TResult extends FeatureResult> {
    boolean itApplies(TResult result);
    ResponseEntity<BaseResponse> run(TResult result);
}
