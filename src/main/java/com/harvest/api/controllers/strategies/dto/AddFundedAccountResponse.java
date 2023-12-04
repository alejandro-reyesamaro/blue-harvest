package com.harvest.api.controllers.strategies.dto;

import lombok.Getter;

@Getter
public class AddFundedAccountResponse extends AddEmptyAccountResponse {
    
    protected String initialCredit;
    protected String date;

    public AddFundedAccountResponse(String accountName, String initialCredit, String date, String message) {
        super(accountName, message);
        this.initialCredit = initialCredit;
        this.date = date;
    }
}
