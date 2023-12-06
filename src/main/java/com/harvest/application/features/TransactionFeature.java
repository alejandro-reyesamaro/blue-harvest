package com.harvest.application.features;

import java.util.Collection;
import java.util.Optional;

import com.harvest.application.features.dto.AddTransactionResult;
import com.harvest.application.features.dto.GetCostumerTransactionsResult;
import com.harvest.application.services.IAccountService;
import com.harvest.application.services.ICostumerService;
import com.harvest.application.services.ITransactionService;
import com.harvest.application.services.dto.forms.AddTransactionForm;
import com.harvest.core.entities.Account;
import com.harvest.core.entities.Costumer;
import com.harvest.core.entities.Transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TransactionFeature {
    
    @Autowired
    protected ITransactionService transactionService;

    @Autowired
    protected ICostumerService costumerService;

    @Autowired
    protected IAccountService accountService;

    public Optional<Transaction> getTransactionById(int id) {
        return transactionService.getTransactionById(id);
    }

    public GetCostumerTransactionsResult getCostumerTransactions(int costumerId) {
        Optional<Costumer> costumer = this.costumerService.getCostumerById(costumerId);
        if(costumer.isPresent()) {
            Collection<Transaction> transactions = transactionService.getCostumerDetailedTransactions(costumerId);
            return transactions.size() > 0 
                ? GetCostumerTransactionsResult.success(costumer.get(), transactions)
                : GetCostumerTransactionsResult.notTransactionsFound();
        }
        else return GetCostumerTransactionsResult.costumerNotFound();
    }

    public AddTransactionResult addTransaction(AddTransactionForm form) {
        if(form.getAmount() > 0) {
            return positiveTransaction(form);
        }
        else return AddTransactionResult.negativeTransaction();
    }

    private AddTransactionResult positiveTransaction(AddTransactionForm form) {
        Optional<Costumer> costumer = this.costumerService.getCostumerById(form.getCostumerId());
        if(costumer.isPresent()) {
            return createTransactionCostumerPresent(form, costumer.get());
        }
        else return AddTransactionResult.costumerNotFound();
    }

    private AddTransactionResult createTransactionCostumerPresent(AddTransactionForm form, Costumer costumer) {
        Optional<Account> costumerAccount = this.accountService.getAccountById(form.getCostumerAccountId());
        if(costumerAccount.isPresent()) {
            return createTransactionCostumerAccountPresent(form, costumer, costumerAccount.get());
        }
        else return AddTransactionResult.costumerAccountNotFound();
    }

    private AddTransactionResult createTransactionCostumerAccountPresent(AddTransactionForm form, Costumer costumer, Account costumerAccount) {
        if(costumerAccount.getBalance() > form.getAmount()) {
            Optional<Account> targetAccount = this.accountService.getAccountById(form.getTargetAccountId());
            if(targetAccount.isPresent()) {
                return createTransactionAccountsPresent(form, costumer, costumerAccount, targetAccount.get());
            }
            else return AddTransactionResult.targetAccountNotFound();
        }
        else return AddTransactionResult.notEnoughBalance();
    }        

    private AddTransactionResult createTransactionAccountsPresent(
            AddTransactionForm form, 
            Costumer costumer,
            Account costumerAccount,
            Account targetAccount) {

        // Save the transaction
        Transaction transaction = transactionService.addTransaction(new AddTransactionForm(
            costumer.getId(), 
            costumerAccount.getId(),
            targetAccount.getId(), 
            form.getAmount())
        );
        // Update the balance
        this.accountService.aggregateBalance(costumerAccount.getId(), -form.getAmount());
        this.accountService.aggregateBalance(targetAccount.getId(), form.getAmount());
        return AddTransactionResult.transactionCreated(transaction);
    }
}
