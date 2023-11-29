package com.harvest.application.feature;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collections;
import java.util.Optional;

import com.harvest.application.features.InjectionFeature;
import com.harvest.application.features.dto.AddInjectionResult;
import com.harvest.application.features.dto.GetCostumerInjectionsResult;
import com.harvest.application.services.IAccountService;
import com.harvest.application.services.ICostumerService;
import com.harvest.application.services.IInjectionService;
import com.harvest.application.services.dto.forms.AddInjectionForm;
import com.harvest.core.entities.Injection;
import com.harvest.testools.factories.AccountFactory;
import com.harvest.testools.factories.AddInjectionFormFactory;
import com.harvest.testools.factories.CostumerFactory;
import com.harvest.testools.factories.InjectionFactory;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class InjectionFeatureTests {
    
    @Mock
    protected IAccountService accountService;

    @Mock
    protected ICostumerService costumerService;

    @Mock
    protected IInjectionService injectionService;

    @InjectMocks
    protected InjectionFeature feature;

    @Test
    public void getInjectionById_success() {
        // Arrange
        int id = 1;
        when(injectionService.getInjectionById(id))
        .thenReturn(Optional.of(InjectionFactory.buildInjection(1, id, 10, 10)));

        // Act 
        Optional<Injection> result = feature.getInjectionById(id);

        // Assert
        assertThat(result.get().getId()).isEqualTo(id);
    }

    @Test
    public void getCostumerInjections_noCostumer() {
        // Arrange
        int costumerId = 1;
        when(costumerService.getCostumerById(costumerId))
        .thenReturn(Optional.empty());

        // Act
        GetCostumerInjectionsResult result = feature.getCostumerInjections(costumerId);
        
        // Assert
        assertThat(result.isSuccess()).isFalse();
        assertThat(result.getInjections().size()).isEqualTo(0);
        assertThat(result.getMessage()).isEqualTo(GetCostumerInjectionsResult.NO_COSTUMER_FOUND);
        verify(injectionService, times(0)).getCostumerInjections(anyInt());
    }

    @Test
    public void getCostumerInjections_noInjections() {
        // Arrange
        int costumerId = 1;
        when(costumerService.getCostumerById(costumerId))
        .thenReturn(Optional.of(CostumerFactory.buildCostumer(costumerId)));
        when(injectionService.getCostumerInjections(costumerId))
        .thenReturn(Collections.emptyList());

        // Act
        GetCostumerInjectionsResult result = feature.getCostumerInjections(costumerId);
        
        // Assert
        assertThat(result.isSuccess()).isTrue();
        assertThat(result.getInjections().size()).isEqualTo(0);
        assertThat(result.getMessage()).isEqualTo(GetCostumerInjectionsResult.NO_INJECTIONS_FOUND);
    }

    @Test
    public void getCostumerInjections_success() {
        // Arrange
        int costumerId = 1;
        when(costumerService.getCostumerById(costumerId))
        .thenReturn(Optional.of(CostumerFactory.buildCostumer(costumerId)));
        when(injectionService.getCostumerInjections(costumerId))
        .thenReturn(InjectionFactory.buildInjections4Test(costumerId, 4));

        // Act
        GetCostumerInjectionsResult result = feature.getCostumerInjections(costumerId);
        
        // Assert
        assertThat(result.isSuccess()).isTrue();
        assertThat(result.getInjections().size()).isEqualTo(4);
        assertThat(result.getMessage()).endsWith(GetCostumerInjectionsResult.COSTUMER_INJECTIONS_SUFFIX);
    }

    @Test
    public void addInjection_negativeAmount() {
        // Arrange
        AddInjectionForm form = AddInjectionFormFactory.buildForm(1, 10, -10);
        
        // Act
        AddInjectionResult result = feature.addInjection(form);

        // Assert
        assertThat(result.isSuccess()).isFalse();
        assertThat(result.getMessage()).isEqualTo(AddInjectionResult.NEGATIVE_AMOUNT);
    }

    @Test
    public void addInjection_noCostumer() {
        // Arrange
        int costumerId = 1;
        when(costumerService.getCostumerById(costumerId))
        .thenReturn(Optional.empty());

        // Act
        AddInjectionResult result = feature.addInjection(AddInjectionFormFactory.buildForm(costumerId, 10, 10));
        
        // Assert
        assertThat(result.isSuccess()).isFalse();
        assertThat(result.getMessage()).isEqualTo(AddInjectionResult.NO_COSTUMER_FOUND);
        verify(accountService, times(0)).getAccountById(anyInt());
    }

    @Test
    public void addInjection_noAccount() {
        // Arrange
        int costumerId = 1;
        int accountId = 10;
        when(costumerService.getCostumerById(costumerId))
        .thenReturn(Optional.of(CostumerFactory.buildCostumer(costumerId)));
        when(accountService.getAccountById(accountId))
        .thenReturn(Optional.empty());

        // Act
        AddInjectionResult result = feature.addInjection(AddInjectionFormFactory.buildForm(costumerId, accountId, 10));
        
        // Assert
        assertThat(result.isSuccess()).isFalse();
        assertThat(result.getMessage()).isEqualTo(AddInjectionResult.NO_ACCOUNT_FOUND);
        verify(injectionService, times(0)).addInjection(any());
    }

    @Test
    public void addInjection_success() {
        // Arrange
        int costumerId = 1;
        int accountId = 10;
        double credit = 100;
        when(costumerService.getCostumerById(costumerId))
        .thenReturn(Optional.of(CostumerFactory.buildCostumer(costumerId)));
        when(accountService.getAccountById(accountId))
        .thenReturn(Optional.of(AccountFactory.buildAccount(accountId, costumerId)));
        when(injectionService.addInjection(any()))
        .thenReturn(InjectionFactory.buildInjection(1, costumerId, accountId, credit));

        // Act
        AddInjectionResult result = feature.addInjection(AddInjectionFormFactory.buildForm(costumerId, accountId, credit));
        
        // Assert
        assertThat(result.isSuccess()).isTrue();
        assertThat(result.getMessage()).isEqualTo(AddInjectionResult.CREATED);
        assertThat(result.getInjection().getCostumerId()).isEqualTo(costumerId);
        assertThat(result.getInjection().getCostumerAccountId()).isEqualTo(accountId);
        assertThat(result.getInjection().getAmount()).isEqualTo(credit);
    }
}
