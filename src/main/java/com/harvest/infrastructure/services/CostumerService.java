package com.harvest.infrastructure.services;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

import com.harvest.application.services.ICostumerService;
import com.harvest.application.services.dto.forms.AddCostumerForm;
import com.harvest.application.services.dto.results.AddEntityResult;
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
        Optional<CostumerDto> result = costumerRepository.findById(Long.valueOf(id));
        return result.isPresent()
            ? Optional.of(mapper.map(result.get(), Costumer.class))
            : Optional.empty();
            //Optional.of(new Costumer(result.get().getId(), result.get().getName(), result.get().getSurname()));
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
