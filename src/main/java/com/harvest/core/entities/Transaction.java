package com.harvest.core.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Transaction extends Injection {
    protected int targetAccountId;

    public Transaction() {}
}
