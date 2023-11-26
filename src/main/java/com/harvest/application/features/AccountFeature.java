package com.harvest.application.features;

import java.util.Collection;
import java.util.Optional;

import com.harvest.application.features.dto.AddAccountResult;
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

    public Collection<Account> getCostumerAccounts(int costumerId) {
        return accountService.getCostumerAccounts(costumerId);
    }

    public AddAccountResult createAccount(AddAccountForm form) {
        Optional<Costumer> costumer = this.costumerService.getCostumerById(form.getCostumerId());
        if(costumer.isPresent()) {
            Account newAccount = this.accountService.createAccount(form);
            if(form.getInitialCredit() > 0) {
                // Save the transaction
                Injection injection = injectionService.addInjection(new AddInjectionForm(
                    costumer.get().getId(), 
                    newAccount.getId(), 
                    form.getInitialCredit())
                );
                // Update the balance
                this.accountService.aggregateBalance(newAccount.getId(), form.getInitialCredit());
                
                return AddAccountResult.fundedAccountCreated(newAccount, injection);
            }
            else return AddAccountResult.emptyAccountCreated(newAccount);
        }
        else return AddAccountResult.costumerNotFound();
    }
}
