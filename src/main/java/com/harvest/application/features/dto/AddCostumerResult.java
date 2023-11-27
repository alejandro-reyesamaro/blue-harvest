package com.harvest.application.features.dto;

import com.harvest.core.entities.Costumer;

import lombok.Getter;

@Getter
public class AddCostumerResult extends FeatureResult {
    
    protected Costumer costumer;

    public AddCostumerResult(Costumer newCostumer, String message, boolean isSuccess) {
        super(message, isSuccess);
        this.costumer = newCostumer;
    }
}
