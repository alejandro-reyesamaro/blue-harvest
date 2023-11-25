package com.harvest.infrastructure.services;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.harvest.application.services.ICostumerService;
import com.harvest.application.services.dto.forms.AddCostumerForm;
import com.harvest.core.entities.Costumer;
import com.harvest.infrastructure.repository.Costumer.ICostumerRepository;
import com.harvest.infrastructure.repository.Costumer.CostumerDto;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CostumerService implements ICostumerService {
    
    @Autowired
    protected ICostumerRepository costumerRepository;

    @Autowired
    protected ModelMapper mapper; 

    public Optional<Costumer> getCostumerById(int id) {
        Optional<CostumerDto> costumer = costumerRepository.findById(Long.valueOf(id));
        return costumer.isPresent()
            ? Optional.of(mapper.map(costumer.get(), Costumer.class))
            : Optional.empty();
    }

    public Collection<Costumer> getAllCostumers() {
        List<CostumerDto> costumers = costumerRepository.findAll();
        return costumers.stream().map(c -> mapper.map(c, Costumer.class)).collect(Collectors.toList());
    }
    
    public Costumer addCostumer(AddCostumerForm form) {
        CostumerDto costumer = costumerRepository.save(new CostumerDto(form.getName(), form.getSurname()));
        return mapper.map(costumer, Costumer.class);
    }
}
