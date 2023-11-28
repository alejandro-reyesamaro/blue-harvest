package com.harvest.application.features.dto;

import java.util.Collection;
import java.util.Collections;

import com.harvest.core.entities.Transaction;

import lombok.Getter;

@Getter
public class GetCostumerTransactionsResult extends FeatureResult {
    
    protected Collection<Transaction> transactions;

    protected GetCostumerTransactionsResult(Collection<Transaction> transactions, String message, boolean isSuccess) {
        super(message, isSuccess);
        this.transactions = transactions;
    }

    public static GetCostumerTransactionsResult success(Collection<Transaction> transactions) {
        return new GetCostumerTransactionsResult(transactions, transactions.size() + " transaction(s) found", true);
    }

    public static GetCostumerTransactionsResult notTransactionsFound() {
        return new GetCostumerTransactionsResult(Collections.emptyList(), "No transactions found for the given costumer", true);
    }

    public static GetCostumerTransactionsResult costumerNotFound() {
        return new GetCostumerTransactionsResult(Collections.emptyList(), "Costumer not found", false);
    }
}