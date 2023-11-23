package com.harvest.application.services;

import java.util.Collection;

import com.harvest.application.services.dto.forms.AddTransactionForm;
import com.harvest.application.services.dto.results.AddEntityResult;
import com.harvest.core.entities.Transaction;

import org.springframework.stereotype.Component;

@Component
public interface ITransactionService {
    Transaction getTransactionById(int id);
    Collection<Transaction> getCostumerTransactions(int costumerId);
    AddEntityResult addTransaction(AddTransactionForm form);
}
