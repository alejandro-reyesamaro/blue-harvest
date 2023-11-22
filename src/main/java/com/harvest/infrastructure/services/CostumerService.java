package com.harvest.infrastructure.services;

import java.util.Arrays;
import java.util.Collection;

import com.harvest.application.services.ICostumerService;
import com.harvest.application.services.dto.forms.AddCostumerForm;
import com.harvest.application.services.dto.results.AddEntityResult;
import com.harvest.core.entities.Costumer;

public class CostumerService implements ICostumerService {
    
    public Costumer getCostumerById(int id) {
        //!- TODO
        return new Costumer(id, String.format("Client_%s", id), "DOE");
    }

    public Collection<Costumer> getAllCostumers() {
        //!- TODO
        return Arrays.asList(
            new Costumer(1, "Client01", "ONE"),
            new Costumer(2, "Client02", "TWO"),
            new Costumer(3, "Client03", "THREE")
        );
    }
    
    public AddEntityResult addCostumer(AddCostumerForm form) {
        //!- TODO
        return new AddEntityResult(true, 1, "Ok");
    }
}
