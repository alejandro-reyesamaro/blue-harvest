package com.harvest.application.feature;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.Optional;

import com.harvest.application.features.TransactionFeature;
import com.harvest.application.features.dto.AddTransactionResult;
import com.harvest.application.features.dto.GetCostumerTransactionsResult;
import com.harvest.application.services.IAccountService;
import com.harvest.application.services.ICostumerService;
import com.harvest.application.services.ITransactionService;
import com.harvest.application.services.dto.forms.AddTransactionForm;
import com.harvest.core.entities.Transaction;
import com.harvest.testools.factories.AccountFactory;
import com.harvest.testools.factories.AddTransactionFormFactory;
import com.harvest.testools.factories.CostumerFactory;
import com.harvest.testools.factories.TransactionFactory;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class TransactionFeatureTests {
    @Mock
    protected IAccountService accountService;

    @Mock
    protected ICostumerService costumerService;

    @Mock
    protected ITransactionService transactionService;

    @InjectMocks
    protected TransactionFeature feature;

    @Test
    public void getTransactionById_success() {
        // Arrange
        int id = 1;
        when(transactionService.getTransactionById(id))
        .thenReturn(Optional.of(TransactionFactory.buildTransaction(1, id, 10, 10, 10)));

        // Act 
        Optional<Transaction> result = feature.getTransactionById(id);

        // Assert
        assertEquals(id, result.get().getId());
    }

    @Test
    public void getCostumerTransaction_noCostumer() {
        // Arrange
        int costumerId = 1;
        when(costumerService.getCostumerById(costumerId))
        .thenReturn(Optional.empty());

        // Act
        GetCostumerTransactionsResult result = feature.getCostumerTransactions(costumerId);
        
        // Assert
        assertFalse(result.isSuccess());
        assertEquals(0, result.getTransactions().size());
        assertEquals(GetCostumerTransactionsResult.NO_COSTUMER_FOUND, result.getMessage());
        verify(transactionService, times(0)).getCostumerTransactions(anyInt());
    }

    @Test
    public void getCostumerTransactions_noTransaction() {
        // Arrange
        int costumerId = 1;
        when(costumerService.getCostumerById(costumerId))
        .thenReturn(Optional.of(CostumerFactory.buildCostumer(costumerId)));
        when(transactionService.getCostumerTransactions(costumerId))
        .thenReturn(Collections.emptyList());

        // Act
        GetCostumerTransactionsResult result = feature.getCostumerTransactions(costumerId);
        
        // Assert
        assertTrue(result.isSuccess());
        assertEquals(0, result.getTransactions().size());
        assertEquals(GetCostumerTransactionsResult.NO_TRANSACTION_FOUND, result.getMessage());
    }

    @Test
    public void getCostumerTransactions_success() {
        // Arrange
        int costumerId = 1;
        when(costumerService.getCostumerById(costumerId))
        .thenReturn(Optional.of(CostumerFactory.buildCostumer(costumerId)));
        when(transactionService.getCostumerTransactions(costumerId))
        .thenReturn(TransactionFactory.buildTransactions4Test(costumerId, 4));

        // Act
        GetCostumerTransactionsResult result = feature.getCostumerTransactions(costumerId);
        
        // Assert
        assertTrue(result.isSuccess());
        assertEquals(4, result.getTransactions().size());
        assertTrue(result.getMessage().endsWith(GetCostumerTransactionsResult.COSTUMER_TRANSACTIONS_SUFFIX));
    }

    @Test
    public void addInjection_negativeAmount() {
        // Arrange
        AddTransactionForm form = AddTransactionFormFactory.buildForm(1, 10, 10, -10);
        
        // Act
        AddTransactionResult result = feature.addTransaction(form);

        // Assert
        assertFalse(result.isSuccess());
        assertEquals(AddTransactionResult.NEGATIVE_TRANSACTION, result.getMessage());
    }

    @Test
    public void addTransaction_noCostumer() {
        // Arrange
        int costumerId = 1;
        when(costumerService.getCostumerById(costumerId))
        .thenReturn(Optional.empty());

        // Act
        AddTransactionResult result = feature.addTransaction(AddTransactionFormFactory.buildForm(costumerId, 10, 10, 10));
        
        // Assert
        assertFalse(result.isSuccess());
        assertEquals(AddTransactionResult.COSTUMER_NOT_FOUND, result.getMessage());
        verify(accountService, times(0)).getAccountById(anyInt());
    }

    @Test
    public void addTransaction_noCostumerAccount() {
        // Arrange
        int costumerId = 1;
        int accountId = 10;
        when(costumerService.getCostumerById(costumerId))
        .thenReturn(Optional.of(CostumerFactory.buildCostumer(costumerId)));
        when(accountService.getAccountById(accountId))
        .thenReturn(Optional.empty());

        // Act
        AddTransactionResult result = feature.addTransaction(AddTransactionFormFactory.buildForm(costumerId, accountId, accountId + 10, 10));
        
        // Assert
        assertFalse(result.isSuccess());
        assertEquals(AddTransactionResult.COSTUMER_ACCOUNT_NOT_FOUND, result.getMessage());
        verify(transactionService, times(0)).addTransaction(any());
    }

    @Test
    public void addTransaction_noTargetAccount() {
        // Arrange
        int costumerId = 1;
        int accountId = 10;
        int targetAccountId = 20;
        when(costumerService.getCostumerById(costumerId))
        .thenReturn(Optional.of(CostumerFactory.buildCostumer(costumerId)));
        when(accountService.getAccountById(accountId))
        .thenReturn(Optional.of(AccountFactory.buildAccount(accountId, costumerId, 100)))
        .thenReturn(Optional.empty());

        // Act
        AddTransactionResult result = feature.addTransaction(AddTransactionFormFactory.buildForm(costumerId, accountId, targetAccountId, 10));
        
        // Assert
        assertFalse(result.isSuccess());
        assertEquals(AddTransactionResult.TARGET_ACCOUNT_NOT_FOUND, result.getMessage());
        verify(transactionService, times(0)).addTransaction(any());
    }

    @Test
    public void addTransaction_noEnoughBalance() {
        // Arrange
        int costumerId = 1;
        int accountId = 10;
        int targetAccountId = 20;
        when(costumerService.getCostumerById(costumerId))
        .thenReturn(Optional.of(CostumerFactory.buildCostumer(costumerId)));
        when(accountService.getAccountById(accountId))
        .thenReturn(Optional.of(AccountFactory.buildAccount(accountId, costumerId, 10)))
        .thenReturn(Optional.empty());

        // Act
        AddTransactionResult result = feature.addTransaction(AddTransactionFormFactory.buildForm(costumerId, accountId, targetAccountId, 50));
        
        // Assert
        assertFalse(result.isSuccess());
        assertEquals(AddTransactionResult.NOT_ENOUGH_CREDIT, result.getMessage());
        verify(transactionService, times(0)).addTransaction(any());
    }

    @Test
    public void addTransaction_success() {
        // Arrange
        int costumerId = 1;
        int accountId = 10;
        int targetAccountId = 20;
        double credit = 50;
        when(costumerService.getCostumerById(costumerId))
        .thenReturn(Optional.of(CostumerFactory.buildCostumer(costumerId)));
        when(accountService.getAccountById(accountId))
        .thenReturn(Optional.of(AccountFactory.buildAccount(accountId, costumerId, 200)));
        when(accountService.getAccountById(targetAccountId))
        .thenReturn(Optional.of(AccountFactory.buildAccount(targetAccountId, costumerId, 100)));
        when(transactionService.addTransaction(any()))
        .thenReturn(TransactionFactory.buildTransaction(1, costumerId, accountId, targetAccountId, credit));

        // Act
        AddTransactionResult result = feature.addTransaction(AddTransactionFormFactory.buildForm(costumerId, accountId, targetAccountId, credit));
        
        // Assert
        assertTrue(result.isSuccess());
        assertEquals(AddTransactionResult.CREATED, result.getMessage());
        assertEquals(costumerId, result.getTransaction().getCostumerId());
        assertEquals(accountId, result.getTransaction().getCostumerAccountId());
        assertEquals(credit, result.getTransaction().getAmount());
    }
}
