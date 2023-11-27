package com.harvest.api.controllers.strategies.dto;

import java.util.Collection;

import com.harvest.core.entities.Costumer;

import lombok.Getter;

@Getter
public class GetAllCostumersResponse extends BaseResponse {
    
    protected Collection<Costumer> costumers;

    public GetAllCostumersResponse(Collection<Costumer> costumers, String message) {
        super(message);
        this.costumers = costumers;
    }
}
