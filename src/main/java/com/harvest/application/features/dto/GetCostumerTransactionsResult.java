package com.harvest.application.features.dto;

import java.util.Collection;
import java.util.Collections;

import com.harvest.core.entities.Costumer;
import com.harvest.core.entities.Transaction;

import lombok.Getter;

@Getter
public class GetCostumerTransactionsResult extends FeatureResult {
    
    public static final String NO_COSTUMER_FOUND = "Costumer not found";
    public static final String NO_TRANSACTION_FOUND = "No accounts found for the given costumer";
    public static final String COSTUMER_TRANSACTIONS_SUFFIX = " account(s) found";

    protected Collection<Transaction> transactions;
    protected Costumer costumer;

    protected GetCostumerTransactionsResult(Costumer costumer, Collection<Transaction> transactions, String message, boolean isSuccess) {
        super(message, isSuccess);
        this.transactions = transactions;
        this.costumer = costumer;
    }

    public static GetCostumerTransactionsResult success(Costumer costumer, Collection<Transaction> transactions) {
        return new GetCostumerTransactionsResult(costumer, transactions, transactions.size() + COSTUMER_TRANSACTIONS_SUFFIX, true);
    }

    public static GetCostumerTransactionsResult notTransactionsFound() {
        return new GetCostumerTransactionsResult(null, Collections.emptyList(), NO_TRANSACTION_FOUND, true);
    }

    public static GetCostumerTransactionsResult costumerNotFound() {
        return new GetCostumerTransactionsResult(null, Collections.emptyList(), NO_COSTUMER_FOUND, false);
    }
}