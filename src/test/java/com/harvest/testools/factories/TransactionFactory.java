package com.harvest.testools.factories;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import com.harvest.core.entities.Transaction;
import com.harvest.infrastructure.repository.transaction.DetailedTransactionDto;

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

    public static DetailedTransactionDto buildDetailedTransactionDto(int id, int costumerId, int accountId, int targetAccountId, double credit) {
        DetailedTransactionDto transaction = new DetailedTransactionDto();
        transaction.setId(id);
        transaction.setCostumerId(costumerId);
        transaction.setCostumerAccountId(accountId);
        transaction.setAmount(credit);
        transaction.setDate(new Date());
        transaction.setTargetAccountId(targetAccountId);
        transaction.setCostumerAccountName("Costumer_Account_" + id);
        transaction.setTargetAccountName("Target_Account_" + id);
        transaction.setTargetAccountCostumerName("TC_" + id);
        
        return transaction;
    }

    public static List<DetailedTransactionDto> buildDetailedTransactionDtos4Test(int costumerId, int count) {
        List<DetailedTransactionDto> list = new ArrayList<DetailedTransactionDto>();
        for(int i = 0; i < count; i++) {
            list.add(buildDetailedTransactionDto(i + 1, costumerId, 10 + costumerId, 20 + costumerId, 1000 * (i + 1)));
        }
        return list;
    }
}
