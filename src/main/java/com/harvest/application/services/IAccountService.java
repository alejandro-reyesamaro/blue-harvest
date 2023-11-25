package com.harvest.application.services;

import java.util.Collection;

import com.harvest.application.services.dto.forms.AddAccountForm;
import com.harvest.application.services.dto.results.AddEntityResult;
import com.harvest.core.entities.Account;

public interface IAccountService {
    Account getAccountById(int id);
    Collection<Account> getCostumerAccounts(int id);
    AddEntityResult createAccount(AddAccountForm form);
}
