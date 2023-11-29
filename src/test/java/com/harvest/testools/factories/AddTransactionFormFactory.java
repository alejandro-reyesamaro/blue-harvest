package com.harvest.testools.factories;

import com.harvest.application.services.dto.forms.AddTransactionForm;

public class AddTransactionFormFactory {
 
    public static AddTransactionForm buildForm(int costumerId, int costumerAccountId, int targetAccountId, double amount) {
        AddTransactionForm form = new AddTransactionForm();
        form.setCostumerId(costumerId);
        form.setCostumerAccountId(costumerAccountId);
        form.setTargetAccountId(targetAccountId);
        form.setAmount(amount);
        return form; 
    }
}
