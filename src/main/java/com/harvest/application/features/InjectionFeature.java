package com.harvest.application.features;

import java.util.Collection;
import java.util.Optional;

import com.harvest.application.features.dto.AddInjectionResult;
import com.harvest.application.features.dto.GetCostumerInjectionsResult;
import com.harvest.application.services.IAccountService;
import com.harvest.application.services.ICostumerService;
import com.harvest.application.services.IInjectionService;
import com.harvest.application.services.dto.forms.AddInjectionForm;
import com.harvest.core.entities.Account;
import com.harvest.core.entities.Costumer;
import com.harvest.core.entities.Injection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InjectionFeature {
    
    @Autowired
    protected IInjectionService injectionService;

    @Autowired
    protected ICostumerService costumerService;

    @Autowired
    protected IAccountService accountService;

    public Optional<Injection> getInjectionById(int id) {
        return injectionService.getInjectionById(id);
    }

    public GetCostumerInjectionsResult getCostumerInjections(int costumerId) {
        Optional<Costumer> costumer = this.costumerService.getCostumerById(costumerId);
        if(costumer.isPresent()) {
            Collection<Injection> injections = injectionService.getCostumerInjections(costumerId);
            return injections.size() > 0 
                ? GetCostumerInjectionsResult.success(injections)
                : GetCostumerInjectionsResult.notInjectionsFound();
        }
        else return GetCostumerInjectionsResult.costumerNotFound();
    }

    public AddInjectionResult addInjection(AddInjectionForm form) {
        Optional<Costumer> costumer = this.costumerService.getCostumerById(form.getCostumerId());
        if(costumer.isPresent()) {
            return createInjectionCostumerPresent(form, costumer.get());
        }
        else return AddInjectionResult.costumerNotFound();
    }

    private AddInjectionResult createInjectionCostumerPresent(AddInjectionForm form, Costumer costumer) {
        Optional<Account> costumerAccount = this.accountService.getAccountById(form.getCostumerAccountId());
        if(costumerAccount.isPresent()) {
            return createFundedAccount(form, costumer, costumerAccount.get());
        }
        else return AddInjectionResult.costumerAccountNotFound();
    }

    private AddInjectionResult createFundedAccount(AddInjectionForm form, Costumer costumer, Account costumerAccount) {
        // Save the transaction
        Injection injection = injectionService.addInjection(new AddInjectionForm(
            costumer.getId(), 
            costumerAccount.getId(), 
            form.getAmount())
        );
        // Update the balance
        this.accountService.aggregateBalance(costumerAccount.getId(), form.getAmount());
        return AddInjectionResult.injectionCreated(injection);
    }
}
