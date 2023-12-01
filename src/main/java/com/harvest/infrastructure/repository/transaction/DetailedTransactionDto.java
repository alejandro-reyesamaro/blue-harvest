package com.harvest.infrastructure.repository.transaction;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "DetailedTransaction")
@Data
public class DetailedTransactionDto {
    
    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected int id;
    
    @Column(name = "CostumerId")
    protected int costumerId;

    @Column(name = "CostumerAccountId")
    protected int costumerAccountId;

    @Column(name = "Amount")
    protected double amount;

    @Column(name = "Date")
    protected Date date;

    @Column(name = "TargetAccountId")
    protected int targetAccountId;

    @Column(name = "SourceAccountName")
    protected String sourceAccountName;

    @Column(name = "SourceAccountBalance")
    protected double sourceAccountBalance;
    
    @Column(name = "TargetAccountName")
    protected String targetAccountName;
    
    @Column(name = "TargetAccountBalance")
    protected double targetAccountBalance;

    @Column(name = "TargetAccountCostumerName")
    protected double targetAccountCostumerName;
}
