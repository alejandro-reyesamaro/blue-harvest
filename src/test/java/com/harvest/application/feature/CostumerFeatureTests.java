package com.harvest.application.feature;

import static org.mockito.Mockito.when;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collection;
import java.util.Optional;

import com.harvest.application.features.CostumerFeature;
import com.harvest.application.features.dto.GetAllCostumersResult;
import com.harvest.application.services.ICostumerService;
import com.harvest.core.entities.Costumer;
import com.harvest.testools.factories.CostumerFactory;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CostumerFeatureTests {
    
    @Mock
    protected ICostumerService costumerService;

    @InjectMocks
    protected CostumerFeature feature;

    @Test
    public void getCostumerById_success() {
        // Arrange
        int id = 1;
        Costumer c = new Costumer();
        c.setId(id);
        when(costumerService.getCostumerById(id))
        .thenReturn(Optional.of(c));

        // Act 
        Optional<Costumer> result = feature.getCostumerById(id);

        // Assert
        assertThat(result.get().getId()).isEqualTo(id);
    }

    @Test
    public void getAllCostumers_noCostumers() {
        // Arrange
        Collection<Costumer> costumers = CostumerFactory.buildCostumers4Test(0);
        when(costumerService.getAllCostumers())
        .thenReturn(costumers);

        // Act 
        GetAllCostumersResult result = feature.getAllCostumers();

        // Assert
        assertThat(result.isSuccess()).isTrue();
        assertThat(result.getCostumers().size()).isEqualTo(0);
        assertThat(result.getMessage()).isEqualTo(GetAllCostumersResult.NO_COSTUMER_FOUND);
    }

    @Test
    public void getAllCostumers_costumersFound() {
        // Arrange
        Collection<Costumer> costumers = CostumerFactory.buildCostumers4Test(2);
        when(costumerService.getAllCostumers())
        .thenReturn(costumers);

        // Act 
        GetAllCostumersResult result = feature.getAllCostumers();

        // Assert
        assertThat(result.isSuccess()).isTrue();
        assertThat(result.getCostumers().size()).isEqualTo(2);
        assertThat(result.getMessage()).endsWith(GetAllCostumersResult.COSTUMERS_FOUND_SUFFIX);
    }
}
