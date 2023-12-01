package com.harvest.testools.factories;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.harvest.core.entities.Transaction;

public class TransactionFactory {
    
    public static Transaction buildTransaction(int id, int costumerId, int accountId, int targetAccountId, double credit) {
        Transaction transaction = new Transaction();
        transaction.setId(id);
        transaction.setCostumer(CostumerFactory.buildCostumer(costumerId));
        transaction.setCostumerAccount(AccountFactory.buildAccount(accountId, costumerId));
        transaction.setTargetAccount(AccountFactory.buildAccount(targetAccountId, costumerId + 10));
        transaction.setAmount(credit);
        return transaction;
    }

    public static Collection<Transaction> buildTransactions4Test(int costumerId, int count) {
        List<Transaction> list = new ArrayList<Transaction>();
        for(int i = 0; i < count; i++) {
            list.add(buildTransaction(i + 1, costumerId, 10 + costumerId, 20 + costumerId, 1000 * (i + 1)));
        }
        return list;
    }
}
