package com.harvest.api.controllers.strategies.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TransactionInfoResponse {
    
    protected int id;

    protected int costumerAccountId;
    protected String costumerAccountName;

    protected int targetAccountId;
    protected String targetAccountName;
    protected String targetAccountCostumerName;
    protected String targetAccountCostumerSurname;

    protected double amount;
    protected Date date;
}
