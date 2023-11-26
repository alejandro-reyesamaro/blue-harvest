package com.harvest.application.services.dto.forms;

import lombok.Data;
import jakarta.validation.constraints.*;

@Data
public class AddCostumerForm {
    
    @NotEmpty(message = "The name is required.")
    @Size(min = 2, max = 100, message = "The length of the name must be between 2 and 100 characters.")
    protected String name;

    @NotEmpty(message = "The surname is required.")
    @Size(min = 2, max = 100, message = "The length of the surname must be between 2 and 100 characters.")
    protected String surname;
}
