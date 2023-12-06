package com.harvest.application.features.dto;

import java.util.Collection;
import java.util.Collections;

import com.harvest.core.entities.Account;
import com.harvest.core.entities.Costumer;

import lombok.Getter;

@Getter
public class GetCostumerAccountsResult extends FeatureResult {

    public static final String NO_COSTUMER_FOUND = "Costumer not found";
    public static final String NO_ACCOUNT_FOUND = "No accounts found for the given costumer";
    public static final String COSTUMER_ACCOUNTS_SUFFIX = " account(s) found";

    protected Collection<Account> accounts;
    protected Costumer costumer;

    protected GetCostumerAccountsResult(Costumer costumer, Collection<Account> accounts, String message, boolean isSuccess) {
        super(message, isSuccess);
        this.accounts = accounts;
        this.costumer = costumer;
    }

    public static GetCostumerAccountsResult costumerNotFound() {
        return new GetCostumerAccountsResult(null, Collections.emptyList(), NO_COSTUMER_FOUND, false);
    }

    public static GetCostumerAccountsResult notAccountsFound() {
        return new GetCostumerAccountsResult(null, Collections.emptyList(), NO_ACCOUNT_FOUND, true);
    }

    public static GetCostumerAccountsResult success(Costumer costumer, Collection<Account> accounts) {
        return new GetCostumerAccountsResult(costumer, accounts, accounts.size() + COSTUMER_ACCOUNTS_SUFFIX, true);
    }
}
