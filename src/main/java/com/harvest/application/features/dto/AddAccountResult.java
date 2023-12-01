package com.harvest.application.features.dto;

import com.harvest.core.entities.Account;
import com.harvest.core.entities.Injection;

import lombok.Getter;

@Getter
public class AddAccountResult extends FeatureResult {

    public static final String NEGATIVE_ACCOUNT = "Initial credit must be a non-negative number";
    public static final String NO_COSTUMER_FOUND = "Costumer not found";
    public static final String EMPTY_ACCOUNT_CREATED = "Empty account created successfully";
    public static final String FUNDED_ACCOUNT_CREATED = "Funded account created successfully";

    protected Account newAccount;
    protected Injection injection;

    public AddAccountResult(Account newAccount, Injection injection, String message, boolean isSuccess) {
        super(message, isSuccess);
        this.newAccount = newAccount;
        this.injection = injection;
    }

    public static AddAccountResult negativeAccount() {
        return new AddAccountResult(null, null, NEGATIVE_ACCOUNT, false);
    }

    public static AddAccountResult costumerNotFound() {
        return new AddAccountResult(null, null, NO_COSTUMER_FOUND, false);
    }

    public static AddAccountResult emptyAccountCreated(Account account) {
        return new AddAccountResult(account, null, EMPTY_ACCOUNT_CREATED, true);
    }

    public static AddAccountResult fundedAccountCreated(Account account, Injection injection) {
        return new AddAccountResult(account, injection, FUNDED_ACCOUNT_CREATED, true);
    }
}
