package com.harvest.api.controllers.strategies.dto;

import lombok.Getter;

@Getter
public class AddTransactionResponse extends BaseResponse {
    
    protected String costumerAccount;
    protected String targetAccount;
    protected String amount;
    protected String date;

    public AddTransactionResponse(String costumerAccount, String targetAccount, String amount, String date, String message) {
        super(message);
        this.costumerAccount = costumerAccount;
        this.targetAccount = targetAccount;
        this.amount = amount;
        this.date = date;
    }
}