package com.harvest.application.feature;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.Optional;

import com.harvest.application.features.AccountFeature;
import com.harvest.application.features.dto.AddAccountResult;
import com.harvest.application.features.dto.GetCostumerAccountsResult;
import com.harvest.application.services.IAccountService;
import com.harvest.application.services.ICostumerService;
import com.harvest.application.services.IInjectionService;
import com.harvest.application.services.dto.forms.AddAccountForm;
import com.harvest.core.entities.Account;
import com.harvest.testools.factories.AccountFactory;
import com.harvest.testools.factories.AddAccountFormFactory;
import com.harvest.testools.factories.CostumerFactory;
import com.harvest.testools.factories.InjectionFactory;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class AccountFeatureTests {
    
    @Mock
    protected IAccountService accountService;

    @Mock
    protected ICostumerService costumerService;

    @Mock
    protected IInjectionService injectionService;

    @InjectMocks
    protected AccountFeature feature;

    @Test
    public void getAccountById_success() {
        // Arrange
        int id = 1;
        when(accountService.getAccountById(id))
        .thenReturn(Optional.of(AccountFactory.buildAccount(id, 10)));

        // Act 
        Optional<Account> result = feature.getAccountById(id);

        // Assert
        assertEquals(id, result.get().getId());
    }

    @Test
    public void getCostumerAccounts_noCostumer() {
        // Arrange
        int costumerId = 1;
        when(costumerService.getCostumerById(costumerId))
        .thenReturn(Optional.empty());

        // Act
        GetCostumerAccountsResult result = feature.getCostumerAccounts(costumerId);
        
        // Assert
        assertFalse(result.isSuccess());
        assertEquals(GetCostumerAccountsResult.NO_COSTUMER_FOUND, result.getMessage());
        verify(accountService, times(0)).getCostumerAccounts(anyInt());
    }

    @Test
    public void getCostumerAccounts_noAccount() {
        // Arrange
        int costumerId = 1;
        when(costumerService.getCostumerById(costumerId))
        .thenReturn(Optional.of(CostumerFactory.buildCostumer(costumerId)));
        when(accountService.getCostumerAccounts(costumerId))
        .thenReturn(Collections.emptyList());

        // Act
        GetCostumerAccountsResult result = feature.getCostumerAccounts(costumerId);
        
        // Assert
        assertTrue(result.isSuccess());
        assertEquals(0, result.getAccounts().size());
        assertEquals(GetCostumerAccountsResult.NO_ACCOUNT_FOUND, result.getMessage());
    }

    @Test
    public void getCostumerAccounts_success() {
        // Arrange
        int costumerId = 1;
        when(costumerService.getCostumerById(costumerId))
        .thenReturn(Optional.of(CostumerFactory.buildCostumer(costumerId)));
        when(accountService.getCostumerAccounts(costumerId))
        .thenReturn(AccountFactory.getAccounts4Test(costumerId, 2));

        // Act
        GetCostumerAccountsResult result = feature.getCostumerAccounts(costumerId);
        
        // Assert
        assertTrue(result.isSuccess());
        assertEquals(2, result.getAccounts().size());
        assertTrue(result.getMessage().endsWith(GetCostumerAccountsResult.COSTUMER_ACCOUNTS_SUFFIX));
    }

    @Test
    public void createAccount_costumerNotFound() {
        // Arrange
        int costumerId = 1;
        AddAccountForm form = AddAccountFormFactory.buildForm(costumerId, "Test-Form", 0);        
        when(costumerService.getCostumerById(costumerId))
        .thenReturn(Optional.empty());

        // Act
        AddAccountResult result = feature.createAccount(form);
        
        // Assert
        assertFalse(result.isSuccess());
        assertEquals(AddAccountResult.NO_COSTUMER_FOUND, result.getMessage());
        verify(accountService, times(0)).createAccount(any());
    }

    @Test
    public void createAccount_emptyAccount() {
        // Arrange
        int costumerId = 1;
        int accountId = 10;
        AddAccountForm form = AddAccountFormFactory.buildForm(costumerId, "Test-Form", 0);        
        when(costumerService.getCostumerById(costumerId))
        .thenReturn(Optional.of(CostumerFactory.buildCostumer(costumerId)));
        when(accountService.createAccount(form))
        .thenReturn(AccountFactory.buildAccount(accountId, costumerId));

        // Act
        AddAccountResult result = feature.createAccount(form);
        
        // Assert
        assertTrue(result.isSuccess());
        assertEquals(AddAccountResult.EMPTY_ACCOUNT_CREATED, result.getMessage());
        assertEquals(accountId, result.getNewAccount().getId());
        verify(accountService, times(0)).aggregateBalance(anyInt(), anyDouble());
        verify(injectionService, times(0)).addInjection(any());
    }

    @Test
    public void createAccount_fundedAccount() {
        // Arrange
        int costumerId = 1;
        int accountId = 10;
        double credit = 10;
        AddAccountForm form = AddAccountFormFactory.buildForm(costumerId, "Test-Form", credit);        
        when(costumerService.getCostumerById(costumerId))
        .thenReturn(Optional.of(CostumerFactory.buildCostumer(costumerId)));
        when(accountService.createAccount(form))
        .thenReturn(AccountFactory.buildAccount(accountId, costumerId));
        when(injectionService.addInjection(any()))
        .thenReturn(InjectionFactory.buildInjection(1, costumerId, accountId, credit));

        // Act
        AddAccountResult result = feature.createAccount(form);
        
        // Assert
        assertTrue(result.isSuccess());
        assertEquals(AddAccountResult.FUNDED_ACCOUNT_CREATED, result.getMessage());
        assertEquals(accountId, result.getNewAccount().getId());
        assertEquals(credit, result.getInjection().getAmount());
    }
}
