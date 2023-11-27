package com.harvest.api.controllers.strategies.dto;

import java.util.Collection;

import com.harvest.core.entities.Account;

import lombok.Getter;

@Getter
public class GetCostumerAccountsResponse extends BaseResponse {
    
    protected Collection<Account> accounts;

    public GetCostumerAccountsResponse(Collection<Account> accounts, String message) {
        super(message);
        this.accounts = accounts;
    }
}
