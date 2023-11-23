package com.harvest.application.services.dto.forms;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AddTransactionForm {
    private int costumerId;
    private int accountId;
    private double amount;
}
