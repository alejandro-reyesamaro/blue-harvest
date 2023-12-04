package com.harvest.application.features.dto;

import java.util.Collection;
import java.util.Collections;

import com.harvest.core.entities.Transaction;

import lombok.Getter;

@Getter
public class GetCostumerTransactionsResult extends FeatureResult {
    
    public static final String NO_COSTUMER_FOUND = "Costumer not found";
    public static final String NO_TRANSACTION_FOUND = "No accounts found for the given costumer";
    public static final String COSTUMER_TRANSACTIONS_SUFFIX = " account(s) found";

    protected Collection<Transaction> transactions;

    protected GetCostumerTransactionsResult(Collection<Transaction> transactions, String message, boolean isSuccess) {
        super(message, isSuccess);
        this.transactions = transactions;
    }

    public static GetCostumerTransactionsResult success(Collection<Transaction> transactions) {
        return new GetCostumerTransactionsResult(transactions, transactions.size() + COSTUMER_TRANSACTIONS_SUFFIX, true);
    }

    public static GetCostumerTransactionsResult notTransactionsFound() {
        return new GetCostumerTransactionsResult(Collections.emptyList(), NO_TRANSACTION_FOUND, true);
    }

    public static GetCostumerTransactionsResult costumerNotFound() {
        return new GetCostumerTransactionsResult(Collections.emptyList(), NO_COSTUMER_FOUND, false);
    }
}