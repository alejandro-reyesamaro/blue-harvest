package com.harvest.core.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Account {
    private int id;
    private int costumerId;
    private String name;
    private double balance;
}
