package com.harvest.infrastructure.repository.transaction;

import java.util.Date;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "`Transaction`")
@Data
public class TransactionDto {
    
    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @Column(name = "CostumerId")
    private int costumerId;

    @Column(name = "CostumerAccountId")
    private int costumerAccountId;

    @Column(name = "TargetAccountId")
    private int targetAccountId;

    @Column(name = "Amount")
    private double amount;

    @Column(name = "Date")
    private Date date;

    public TransactionDto() {}

    public TransactionDto(int costumerId, int costumerAccountId, int targetAccountId, double amount) {
        this.costumerId = costumerId;
        this.costumerAccountId = costumerAccountId;
        this.targetAccountId = targetAccountId;
        this.date = new Date();
    }

    @Override
    public String toString() {
        return "Transaction [id=" + id + "]";
    }
}
