package com.harvest.application.features;

import com.harvest.application.features.dto.AddCostumerResult;
import com.harvest.application.services.ICostumerService;
import com.harvest.application.services.dto.forms.AddCostumerForm;
import com.harvest.core.entities.Costumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CostumerFeature {
    
    @Autowired
    protected ICostumerService costumerService;
    
    public AddCostumerResult addCostumer(AddCostumerForm form) {
        Costumer newCostumer = this.costumerService.addCostumer(form);
        return new AddCostumerResult(newCostumer, "Costumer created!", true);
    }
}
