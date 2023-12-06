package com.harvest.application.services;

import java.util.Collection;
import java.util.Optional;

import com.harvest.application.services.dto.forms.AddAccountForm;
import com.harvest.core.entities.Account;

public interface IAccountService {
    Optional<Account> getAccountById(int id);
    Collection<Account> getCostumerAccounts(int id);
    Collection<Account> getCostumerDetailedAccounts(int id);
    Account createAccount(AddAccountForm form);
    Optional<Account> aggregateBalance(int id, double amount);
}
