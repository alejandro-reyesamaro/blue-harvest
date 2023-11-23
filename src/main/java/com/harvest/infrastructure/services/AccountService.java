package com.harvest.infrastructure.services;

import java.util.Arrays;
import java.util.Collection;

import com.harvest.application.services.IAccountService;
import com.harvest.application.services.dto.forms.AddAccountForm;
import com.harvest.application.services.dto.results.AddEntityResult;
import com.harvest.core.entities.Account;

public class AccountService implements IAccountService {
    
    public Account getAccountById(int id) {
        //!- TODO
        return new Account(id, 100 + id, String.format("Account_%s", id), 1000.0);
    }

    public Collection<Account> getCostumerAccounts(int id) {
        //!- TODO
        return Arrays.asList(
            new Account(1, id, "Account_1", 1000.0),
            new Account(2, id, "Account_2", 2000.0),
            new Account(3, id, "Account_3", 3000.0)
        );
    }

    public AddEntityResult createAccount(AddAccountForm form) {
        //!- TODO
        return new AddEntityResult(true, 1, "Ok");
    }
}
