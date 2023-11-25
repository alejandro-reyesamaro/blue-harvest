package com.harvest.application.services.dto.forms;

import lombok.AllArgsConstructor;
import lombok.Data;
import jakarta.validation.constraints.*;

@Data
@AllArgsConstructor
public class AddTransactionForm {

    @NotNull(message = "The Costumer ID is required.")
    @Min(value = 1, message = "The Costumer ID must be a number grater that 0")
    private int costumerId;

    @NotNull(message = "The Costumer Account ID is required.")
    @Min(value = 1, message = "The Costumer Account ID must be a number grater that 0")
    private int costumerAccountId;

    @NotNull(message = "The Target Account ID is required.")
    @Min(value = 1, message = "The Target Account ID must be a number grater that 0")
    private int targetAccountId;

    @Min(value = 1, message = "The initial credit must be a non-negative number")
    private double amount;
}
