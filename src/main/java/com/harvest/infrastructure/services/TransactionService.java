package com.harvest.infrastructure.services;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;

import com.harvest.application.services.ITransactionService;
import com.harvest.application.services.dto.forms.AddTransactionForm;
import com.harvest.application.services.dto.results.AddEntityResult;
import com.harvest.core.entities.Transaction;

public class TransactionService implements ITransactionService {
    
    public Transaction getTransactionById(int id) {
        //!- TODO
        return new Transaction(id, 10 + id, 100 + id, 1000.0, new Date());
    }

    public Collection<Transaction> getCostumerTransactions(int costumerId) {
        //!- TODO
        return Arrays.asList(
            new Transaction(1, costumerId, 100 + costumerId, 1000.0, new Date()),
            new Transaction(2, costumerId, 200 + costumerId, 2000.0, new Date()),
            new Transaction(3, costumerId, 300 + costumerId, 3000.0, new Date())
        );
    }

    public AddEntityResult addTransaction(AddTransactionForm form) {
        //!- TODO
        return new AddEntityResult(true, 1, "Ok");
    }
}
