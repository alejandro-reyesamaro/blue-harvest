package com.harvest.core.entities;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Transaction {
    private int id;
    private int costumerId;
    private int accountId;
    private double amount;
    private Date date;
}
