package com.harvest.testools.factories;

import com.harvest.application.services.dto.forms.AddInjectionForm;

public class AddInjectionFormFactory {
    
    public static AddInjectionForm buildForm(int costumerId, int costumerAccountId, double amount) {
        AddInjectionForm form = new AddInjectionForm();
        form.setCostumerId(costumerId);
        form.setCostumerAccountId(costumerAccountId);
        form.setAmount(amount);
        return form; 
    }
}
