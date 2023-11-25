package com.harvest.application.services;

import com.harvest.application.services.dto.forms.AddCostumerForm;
import com.harvest.core.entities.Costumer;

import java.util.Collection;
import java.util.Optional;

public interface ICostumerService {
    Optional<Costumer> getCostumerById(int id);
    Collection<Costumer> getAllCostumers();
    Costumer addCostumer(AddCostumerForm form);
}
