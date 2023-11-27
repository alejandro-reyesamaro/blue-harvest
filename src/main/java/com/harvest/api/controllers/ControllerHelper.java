package com.harvest.api.controllers;

import java.util.List;

import com.harvest.api.controllers.strategies.ICrudResponseStrategy;
import com.harvest.api.controllers.strategies.dto.BaseResponse;
import com.harvest.application.features.dto.FeatureResult;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Component
public class ControllerHelper {
    
    public static <T extends FeatureResult> ResponseEntity<BaseResponse> runStrategies(List<ICrudResponseStrategy<T>> strategies, T result) {
        for(ICrudResponseStrategy<T> s : strategies) {
            if(s.itApplies(result)){
                return s.run(result);
            }
        }
        return new ResponseEntity<>(new BaseResponse("[Error] Bad request"), BAD_REQUEST);
    }
}
