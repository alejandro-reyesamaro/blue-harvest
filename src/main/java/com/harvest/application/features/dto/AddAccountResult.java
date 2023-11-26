package com.harvest.application.features.dto;

import com.harvest.core.entities.Account;
import com.harvest.core.entities.Injection;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AddAccountResult {

    protected Account newAccount;
    protected Injection injection;
    protected String message;
    protected boolean isSuccess;

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
