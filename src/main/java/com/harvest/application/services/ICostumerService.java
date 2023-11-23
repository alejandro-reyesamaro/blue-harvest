package com.harvest.application.services;

import com.harvest.application.services.dto.forms.AddCostumerForm;
import com.harvest.application.services.dto.results.AddEntityResult;
import com.harvest.core.entities.Costumer;

import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public interface ICostumerService {
    Costumer getCostumerById(int id);
    Collection<Costumer> getAllCostumers();
    AddEntityResult addCostumer(AddCostumerForm form);
}
