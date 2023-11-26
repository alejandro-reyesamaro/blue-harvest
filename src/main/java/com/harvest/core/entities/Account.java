package com.harvest.core.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Account {
    protected int id;
    protected int costumerId;
    protected String name;
    protected double balance;

    public Account() {}
}
