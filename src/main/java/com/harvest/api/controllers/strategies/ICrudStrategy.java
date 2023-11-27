package com.harvest.api.controllers.strategies;

import com.harvest.api.controllers.strategies.dto.BaseResponse;

import org.springframework.http.ResponseEntity;

public interface ICrudStrategy<TResult> {
    boolean itApplies(TResult result);
    ResponseEntity<BaseResponse> run(TResult result);
}
