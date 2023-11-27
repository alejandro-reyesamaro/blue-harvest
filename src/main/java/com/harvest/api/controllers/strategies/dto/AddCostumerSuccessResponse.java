package com.harvest.api.controllers.strategies.dto;

import lombok.Getter;

@Getter
public class AddCostumerSuccessResponse extends BaseResponse {
    
    protected String costumer;

    public AddCostumerSuccessResponse(String costumerName, String message) {
        super(message);
        this.costumer = costumerName;
    }
}
