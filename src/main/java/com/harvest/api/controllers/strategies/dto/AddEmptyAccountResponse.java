package com.harvest.api.controllers.strategies.dto;

import lombok.Getter;

@Getter
public class AddEmptyAccountResponse extends BaseResponse {
    
    protected String account;

    public AddEmptyAccountResponse(String accountName, String message) {
        super(message);
        this.account = accountName;
    }
}
