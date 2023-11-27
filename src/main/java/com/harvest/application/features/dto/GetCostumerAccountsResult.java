package com.harvest.application.features.dto;

import java.util.Collection;
import java.util.Collections;

import com.harvest.core.entities.Account;

import lombok.Getter;

@Getter
public class GetCostumerAccountsResult extends FeatureResult {

    protected Collection<Account> accounts;

    protected GetCostumerAccountsResult(Collection<Account> accounts, String message, boolean isSuccess) {
        super(message, isSuccess);
        this.accounts = accounts;
    }

    public static GetCostumerAccountsResult costumerNotFound() {
        return new GetCostumerAccountsResult(Collections.emptyList(), "Costumer not found", false);
    }

    public static GetCostumerAccountsResult notAccountsFound() {
        return new GetCostumerAccountsResult(Collections.emptyList(), "No accounts found for the given costumer", true);
    }

    public static GetCostumerAccountsResult success(Collection<Account> accounts) {
        return new GetCostumerAccountsResult(accounts, accounts.size() + " account(s) found", true);
    }
}
