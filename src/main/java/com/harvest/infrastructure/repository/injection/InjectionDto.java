package com.harvest.infrastructure.repository.injection;

import java.util.Date;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Injection")
@Data
public class InjectionDto {
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

    public InjectionDto() {}

    public InjectionDto(int costumerId, int costumerAccountId, double amount) {
        this.costumerId = costumerId;
        this.costumerAccountId = costumerAccountId;
        this.amount = amount;
        this.date = new Date();
    }

    @Override
    public String toString() {
        return "Injection [id=" + id + "]";
    }
}
