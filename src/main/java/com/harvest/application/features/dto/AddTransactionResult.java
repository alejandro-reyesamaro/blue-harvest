package com.harvest.application.features.dto;

import com.harvest.core.entities.Transaction;

import lombok.Getter;

@Getter
public class AddTransactionResult extends FeatureResult {

    public static final String NOT_ENOUGH_CREDIT = "Not enough credit in the account";
    public static final String NEGATIVE_TRANSACTION = "Transaction amount must be a positive number";
    public static final String COSTUMER_NOT_FOUND = "Costumer not found";
    public static final String COSTUMER_ACCOUNT_NOT_FOUND = "Costumer account not found";
    public static final String TARGET_ACCOUNT_NOT_FOUND = "Target account not found";
    public static final String CREATED = "Transaction created";
    
    protected Transaction transaction;

    protected AddTransactionResult(Transaction transaction, String message, boolean isSuccess) {
        super(message, isSuccess);
        this.transaction = transaction;
    }

    public static AddTransactionResult notEnoughBalance() {
        return new AddTransactionResult(null, NOT_ENOUGH_CREDIT, false);
    }

    public static AddTransactionResult negativeTransaction() {
        return new AddTransactionResult(null, NEGATIVE_TRANSACTION, false);
    }

    public static AddTransactionResult costumerNotFound() {
        return new AddTransactionResult(null, COSTUMER_NOT_FOUND, false);
    }

    public static AddTransactionResult costumerAccountNotFound() {
        return new AddTransactionResult(null, COSTUMER_ACCOUNT_NOT_FOUND, false);
    }

    public static AddTransactionResult targetAccountNotFound() {
        return new AddTransactionResult(null, TARGET_ACCOUNT_NOT_FOUND, false);
    }

    public static AddTransactionResult transactionCreated(Transaction transaction) {
        return new AddTransactionResult(transaction, CREATED, true);
    }
}