package com.harvest.application.services;

import java.util.Collection;
import java.util.Optional;

import com.harvest.application.services.dto.forms.AddInjectionForm;
import com.harvest.core.entities.Injection;

public interface IInjectionService {
    Optional<Injection> getInjectionById(int id);
    Collection<Injection> getCostumerInjections(int costumerId);
    Injection addInjection(AddInjectionForm form);
}
