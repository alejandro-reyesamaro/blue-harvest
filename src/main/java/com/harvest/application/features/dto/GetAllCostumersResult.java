package com.harvest.application.features.dto;

import java.util.Collection;
import java.util.Collections;

import com.harvest.core.entities.Costumer;

import lombok.Getter;

@Getter
public class GetAllCostumersResult extends FeatureResult {
    
    protected Collection<Costumer> costumers;

    protected GetAllCostumersResult(Collection<Costumer> costumers, String message, boolean isSuccess) {
        super(message, isSuccess);
        this.costumers = costumers;
    }

    public static GetAllCostumersResult noCostumers() {
        return new GetAllCostumersResult(Collections.emptyList(), "No Costumer found", true);
    }

    public static GetAllCostumersResult success(Collection<Costumer> costumers) {
        return new GetAllCostumersResult(costumers, costumers.size() + " costumer(s) found", true);
    }
}
