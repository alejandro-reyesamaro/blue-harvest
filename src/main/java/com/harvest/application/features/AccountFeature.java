package com.harvest.application.features;

import java.util.Collection;
import java.util.Optional;

import com.harvest.application.features.dto.AddAccountResult;
import com.harvest.application.features.dto.GetCostumerAccountsResult;
import com.harvest.application.services.IAccountService;
import com.harvest.application.services.ICostumerService;
import com.harvest.application.services.IInjectionService;
import com.harvest.application.services.dto.forms.AddAccountForm;
import com.harvest.application.services.dto.forms.AddInjectionForm;
import com.harvest.core.entities.Account;
import com.harvest.core.entities.Costumer;
import com.harvest.core.entities.Injection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AccountFeature {
    
    @Autowired
    protected IAccountService accountService;

    @Autowired
    protected ICostumerService costumerService;

    @Autowired
    protected IInjectionService injectionService;

    public Optional<Account> getAccountById(int id) {
        return accountService.getAccountById(id);
    }

    public GetCostumerAccountsResult getCostumerAccounts(int costumerId) {
        Optional<Costumer> costumer = this.costumerService.getCostumerById(costumerId);
        if(costumer.isPresent()) {
            Collection<Account> accounts = accountService.getCostumerAccounts(costumerId);
            return accounts.size() > 0 
                ? GetCostumerAccountsResult.success(costumer.get(), accounts)
                : GetCostumerAccountsResult.notAccountsFound();
        }
        else return GetCostumerAccountsResult.costumerNotFound();
    }

    public AddAccountResult createAccount(AddAccountForm form) {
        if(form.getInitialCredit() >= 0) {
            return nonNegativeAccount(form);
        }
        else return AddAccountResult.negativeAccount();
    }

    private AddAccountResult nonNegativeAccount(AddAccountForm form) {
        Optional<Costumer> costumer = this.costumerService.getCostumerById(form.getCostumerId());
        if(costumer.isPresent()) {
            return createAccountCostumerPresent(form, costumer.get());
        }
        else return AddAccountResult.costumerNotFound();
    }

    private AddAccountResult createAccountCostumerPresent(AddAccountForm form, Costumer costumer) {
        Account newAccount = this.accountService.createAccount(form);
        if(form.getInitialCredit() > 0) {
            return createFundedAccount(form, costumer, newAccount);
        }
        else return AddAccountResult.emptyAccountCreated(newAccount);
    }

    private AddAccountResult createFundedAccount(AddAccountForm form, Costumer costumer, Account newAccount) {
        // Save the transaction
        Injection injection = injectionService.addInjection(new AddInjectionForm(
            costumer.getId(), 
            newAccount.getId(), 
            form.getInitialCredit())
        );
        // Update the balance
        this.accountService.aggregateBalance(newAccount.getId(), form.getInitialCredit());
        return AddAccountResult.fundedAccountCreated(newAccount, injection);
    }
}
