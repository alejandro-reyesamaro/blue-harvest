package com.harvest.api.controllers.strategies.dto;

import java.util.Collection;
import java.util.stream.Collectors;

import com.harvest.core.entities.Costumer;
import com.harvest.core.entities.Transaction;

import lombok.Getter;

@Getter
public class GetCostumerTransactionsResponse extends BaseResponse {
    
    protected Collection<TransactionInfoResponse> transactions;
    protected Costumer costumer;

    public GetCostumerTransactionsResponse(Costumer costumer, Collection<Transaction> transactions, String message) {
        super(message);
        this.transactions = transactions.stream().map(t -> new TransactionInfoResponse(
            t.getId(),
            t.getCostumerAccount().getId(),
            t.getCostumerAccount().getName(),
            t.getTargetAccount().getId(),
            t.getTargetAccount().getName(),
            t.getTargetAccount().getCostumer().getName(),
            t.getTargetAccount().getCostumer().getSurname(),
            t.getAmount(),
            t.getDate()
        )).collect(Collectors.toList());
        this.costumer = costumer;
    }
}
