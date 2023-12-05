package com.harvest.infrastructure.services;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.harvest.application.services.IInjectionService;
import com.harvest.application.services.dto.forms.AddInjectionForm;
import com.harvest.core.entities.Injection;
import com.harvest.infrastructure.repository.injection.IInjectionRepository;
import com.harvest.infrastructure.repository.injection.InjectionDto;
import com.harvest.infrastructure.services.mappers.InjectionMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InjectionService implements IInjectionService {
    
    @Autowired
    protected IInjectionRepository injectionRepository;

    @Autowired
    protected InjectionMapper mapper; 

    public Optional<Injection> getInjectionById(int id) {
        Optional<InjectionDto> injection = injectionRepository.findById(Long.valueOf(id));
        return injection.isPresent()
            ? Optional.of(mapper.mapFrom(injection.get()))
            : Optional.empty();
    }

    public Collection<Injection> getCostumerInjections(int costumerId) {
        List<InjectionDto> injection = injectionRepository.findByCostumerId(costumerId);
        return injection.stream().map(c -> mapper.mapFrom(c)).collect(Collectors.toList());
    }

    public Injection addInjection(AddInjectionForm form) {
        InjectionDto injection = injectionRepository.save(new InjectionDto(
            form.getCostumerId(),
            form.getCostumerAccountId(),
            form.getAmount()));
        return mapper.mapFrom(injection);
    }
}
