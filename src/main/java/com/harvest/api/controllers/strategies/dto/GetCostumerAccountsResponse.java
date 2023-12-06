package com.harvest.api.controllers.strategies.dto;

import java.util.Collection;
import java.util.stream.Collectors;

import com.harvest.core.entities.Account;
import com.harvest.core.entities.Costumer;

import lombok.Getter;

@Getter
public class GetCostumerAccountsResponse extends BaseResponse {
    
    protected Collection<AccountInfoResponse> accounts;
    protected Costumer costumer;

    public GetCostumerAccountsResponse(Costumer costumer, Collection<Account> accounts, String message) {
        super(message);
        this.accounts = accounts.stream().map(a -> new AccountInfoResponse(
            a.getId(),
            a.getName(),
            a.getBalance()
        )).collect(Collectors.toList());
        this.costumer = costumer;
    }
}
