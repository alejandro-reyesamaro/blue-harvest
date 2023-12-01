package com.harvest.application.services.dto.forms;

import lombok.Data;
import lombok.EqualsAndHashCode;
import jakarta.validation.constraints.*;

@Data
@EqualsAndHashCode(callSuper=false)
public class AddTransactionForm extends AddInjectionForm {

    @NotNull(message = "The Target Account ID is required.")
    @Min(value = 1, message = "The Target Account ID must be a number grater that 0")
    protected int targetAccountId;

    public AddTransactionForm(){}

    public AddTransactionForm(int costumerId, int costumerAccountId, int targetAccountId, double amount) {
        super(costumerId, costumerAccountId, amount);
        this.targetAccountId = targetAccountId;
    }
}
