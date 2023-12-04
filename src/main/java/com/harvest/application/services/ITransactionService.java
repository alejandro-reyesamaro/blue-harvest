package com.harvest.application.services;

import java.util.Collection;
import java.util.Optional;

import com.harvest.application.services.dto.forms.AddTransactionForm;
import com.harvest.core.entities.Transaction;

public interface ITransactionService {
    Optional<Transaction> getTransactionById(int id);
    Collection<Transaction> getCostumerTransactions(int costumerId);
    Collection<Transaction> getCostumerDetailedTransactions(int costumerId);
    Transaction addTransaction(AddTransactionForm form);
}
