package com.harvest.core.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class Transaction extends Injection {
    protected Account targetAccount;

    public Transaction() {}
}
