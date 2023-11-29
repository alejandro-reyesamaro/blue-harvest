package com.harvest.application.features.dto;

import java.util.Collection;
import java.util.Collections;

import com.harvest.core.entities.Account;

import lombok.Getter;

@Getter
public class GetCostumerAccountsResult extends FeatureResult {

    public static final String NO_COSTUMER_FOUND = "Costumer not found";
    public static final String NO_ACCOUNT_FOUND = "No accounts found for the given costumer";
    public static final String NO_COSTUMER_ACCOUNTS_SUFFIX = " account(s) found";

    protected Collection<Account> accounts;

    protected GetCostumerAccountsResult(Collection<Account> accounts, String message, boolean isSuccess) {
        super(message, isSuccess);
        this.accounts = accounts;
    }

    public static GetCostumerAccountsResult costumerNotFound() {
        return new GetCostumerAccountsResult(Collections.emptyList(), NO_COSTUMER_FOUND, false);
    }

    public static GetCostumerAccountsResult notAccountsFound() {
        return new GetCostumerAccountsResult(Collections.emptyList(), NO_ACCOUNT_FOUND, true);
    }

    public static GetCostumerAccountsResult success(Collection<Account> accounts) {
        return new GetCostumerAccountsResult(accounts, accounts.size() + NO_COSTUMER_ACCOUNTS_SUFFIX, true);
    }
}
