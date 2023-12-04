package com.harvest.api.controllers.strategies.dto;

import java.util.Collection;

import com.harvest.core.entities.Transaction;

import lombok.Getter;

@Getter
public class GetCostumerTransactionsResponse extends BaseResponse {
    
    protected Collection<Transaction> transactions;

    public GetCostumerTransactionsResponse(Collection<Transaction> transactions, String message) {
        super(message);
        this.transactions = transactions;
    }
}
