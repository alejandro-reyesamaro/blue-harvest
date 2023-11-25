package com.harvest.infrastructure.repository.account;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Account")
@Data
public class AccountDto {

    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "CostumerId")
    private int costumerId;

    @Column(name = "Name")
    private String name;

    @Column(name = "Balance")
    private double balance;

    public AccountDto() {}

    public AccountDto(int costumerId, String name, double balance) {
        this.costumerId = costumerId;
        this.name = name;
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Account [id=" + id + ", Name=" + name + ", Balance=" + balance + "]";
    }
}
