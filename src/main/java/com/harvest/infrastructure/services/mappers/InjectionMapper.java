package com.harvest.infrastructure.services.mappers;

import com.harvest.core.entities.Injection;
import com.harvest.infrastructure.repository.injection.InjectionDto;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InjectionMapper implements IMapper<Injection, InjectionDto> {

    @Autowired
    protected ModelMapper mapper;
    
    @Override
    public Injection mapFrom(InjectionDto dto) {
        Injection injection = mapper.map(dto, Injection.class);
        // Repair (set coherent values to non-used fields):
        injection.getCostumer().setName(null);
        injection.getCostumerAccount().getCostumer().setId(0);
        injection.getCostumerAccount().getCostumer().setName(null);
        return injection;
    }
}