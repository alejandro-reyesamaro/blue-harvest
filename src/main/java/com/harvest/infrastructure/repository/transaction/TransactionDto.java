package com.harvest.infrastructure.repository.transaction;

import java.util.Date;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "`Transaction`")
@Data
@EqualsAndHashCode(callSuper=false)
public class TransactionDto {

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
    private int targetAccountId;

    public TransactionDto() {}

    public TransactionDto(int costumerId, int costumerAccountId, int targetAccountId, double amount) {
        this.costumerId = costumerId;
        this.costumerAccountId = costumerAccountId;
        this.amount = amount;
        this.targetAccountId = targetAccountId;
        this.date = new Date();
    }

    @Override
    public String toString() {
        return "Transaction [id=" + id + "]";
    }
}
