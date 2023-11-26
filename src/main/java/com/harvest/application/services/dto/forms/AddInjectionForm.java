package com.harvest.application.services.dto.forms;

import lombok.AllArgsConstructor;
import lombok.Data;
import jakarta.validation.constraints.*;

@Data
@AllArgsConstructor
public class AddInjectionForm {

    @NotNull(message = "The Costumer ID is required.")
    @Min(value = 1, message = "The Costumer ID must be a number grater that 0")
    protected int costumerId;

    @NotNull(message = "The Costumer Account ID is required.")
    @Min(value = 1, message = "The Costumer Account ID must be a number grater that 0")
    protected int costumerAccountId;

    @Min(value = 1, message = "The initial credit must be a non-negative number")
    protected double amount;

    public AddInjectionForm() {}
}
