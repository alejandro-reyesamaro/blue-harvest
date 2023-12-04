package com.harvest.testools.factories;

import com.harvest.application.services.dto.forms.AddAccountForm;

public class AddAccountFormFactory {
    
    public static AddAccountForm buildForm(int costumerId, String name, double initialCredit) {
        AddAccountForm form = new AddAccountForm();
        form.setCostumerId(costumerId);
        form.setName(name);
        form.setInitialCredit(initialCredit);
        return form;
    }
}
