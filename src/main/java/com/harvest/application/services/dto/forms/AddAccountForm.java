package com.harvest.application.services.dto.forms;

import lombok.Data;
import jakarta.validation.constraints.*;

@Data
public class AddAccountForm {

    @NotNull(message = "The Costumer ID is required.")
    @Min(value = 1, message = "The Costumer ID must be a number grater that 0")
    protected int costumerId;

    @NotEmpty(message = "The Account name is required.")
    @Size(min = 2, max = 100, message = "The length of the Account name must be between 2 and 100 characters.")
    protected String name;

    @Min(value = 0, message = "The initial credit must be a positive number")
    protected double initialCredit;
}