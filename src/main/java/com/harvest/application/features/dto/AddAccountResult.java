package com.harvest.application.features.dto;

import com.harvest.core.entities.Account;
import com.harvest.core.entities.Injection;

import lombok.Getter;

@Getter
public class AddAccountResult extends AccountResult {

    protected Account newAccount;
    protected Injection injection;

    public AddAccountResult(Account newAccount, Injection injection, String message, boolean isSuccess) {
        super(message, isSuccess);
        this.newAccount = newAccount;
        this.injection = injection;
    }

    public static AddAccountResult costumerNotFound() {
        return new AddAccountResult(null, null, "Costumer not found", false);
    }

    public static AddAccountResult emptyAccountCreated(Account account) {
        return new AddAccountResult(account, null, "Empty account created successfully", true);
    }

    public static AddAccountResult fundedAccountCreated(Account account, Injection injection) {
        return new AddAccountResult(account, injection, "Funded account created successfully", true);
    }
}
