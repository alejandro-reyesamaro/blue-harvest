package com.harvest.core.entities;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Injection {
    protected int id;
    protected Costumer costumer;
    protected Account costumerAccount;
    protected double amount;
    protected Date date;

    public Injection() {}
}
