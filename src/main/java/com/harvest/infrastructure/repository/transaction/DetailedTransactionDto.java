package com.harvest.infrastructure.repository.transaction;

import java.util.Date;

import org.springframework.data.annotation.Immutable;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity(name = "DetailedTransaction")
@Immutable
@Data
@AllArgsConstructor
public class DetailedTransactionDto {
    
    @Id
    protected int id;

    protected int costumerId;
    protected int costumerAccountId;
    protected String costumerAccountName;

    protected int targetAccountId;
    protected String targetAccountName;
    protected String targetAccountCostumerName;
    protected String targetAccountCostumerSurname;

    protected double amount;
    protected Date date;

    public DetailedTransactionDto(){}
}
