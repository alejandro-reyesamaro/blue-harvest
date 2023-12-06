package com.harvest.api.controllers.strategies.dto;

import java.util.Collection;
import java.util.stream.Collectors;

import com.harvest.core.entities.Account;
import com.harvest.infrastructure.repository.account.DetailedAccountDto;

import lombok.Getter;

@Getter
public class GetCostumerAccountsResponse extends BaseResponse {
    
    protected Collection<DetailedAccountDto> accounts;

    public GetCostumerAccountsResponse(Collection<Account> accounts, String message) {
        super(message);
        this.accounts = accounts.stream().map(a -> new DetailedAccountDto(
            a.getId(),
            a.getName(),
            a.getBalance(),
            a.getCostumer().getId(),
            a.getCostumer().getName(),
            a.getCostumer().getSurname()
        )).collect(Collectors.toList());
    }
}
