package com.harvest.api.controllers.strategies.dto;

import lombok.Getter;

@Getter
public class AddInjectionResponse extends BaseResponse {
    
    protected String account;
    protected String amount;
    protected String date;

    public AddInjectionResponse(String account, String amount, String date, String message) {
        super(message);
        this.account = account;
        this.amount = amount;
        this.date = date;
    }
}
