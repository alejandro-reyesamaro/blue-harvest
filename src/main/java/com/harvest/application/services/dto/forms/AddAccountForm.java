package com.harvest.application.services.dto.forms;

import lombok.Data;
import jakarta.validation.constraints.*;

@Data
public class AddAccountForm {

    public static final String COSTUMER_ID_NULL_ERR_MSG = "The Costumer ID is required.";
    public static final String COSTUMER_ID_MIN_ERR_MSG = "The Costumer ID must be a number grater that 0";
    public static final String NAME_REQUIRED_ERR_MSG = "The Account name is required.";
    public static final String NAME_SIZE_ERR_MSG = "The length of the Account name must be between 2 and 100 characters.";
    public static final String CREDIT_MIN_ERR_MSG = "The initial credit must be a non negative number";

    @NotNull(message = COSTUMER_ID_NULL_ERR_MSG)
    @Min(value = 1, message = COSTUMER_ID_MIN_ERR_MSG)
    protected int costumerId;

    @NotEmpty(message = NAME_REQUIRED_ERR_MSG)
    @Size(min = 2, max = 100, message = NAME_SIZE_ERR_MSG)
    protected String name;

    @Min(value = 0, message = CREDIT_MIN_ERR_MSG)
    protected double initialCredit;
}