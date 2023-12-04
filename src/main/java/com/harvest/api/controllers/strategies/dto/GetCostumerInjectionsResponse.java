package com.harvest.api.controllers.strategies.dto;

import java.util.Collection;

import com.harvest.core.entities.Injection;

import lombok.Getter;

@Getter
public class GetCostumerInjectionsResponse extends BaseResponse {
    
    protected Collection<Injection> injections;

    public GetCostumerInjectionsResponse(Collection<Injection> injections, String message) {
        super(message);
        this.injections = injections;
    }
}
