package com.harvest.infrastructure.services;

import static org.mockito.Mockito.when;

import java.util.List;

import com.harvest.core.entities.Transaction;
import com.harvest.infrastructure.repository.transaction.DetailedTransactionDto;
import com.harvest.infrastructure.repository.transaction.IDetailedTransactionRepository;
import com.harvest.testools.factories.TransactionFactory;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class TransactionServiceTests {
    
    @Mock
    protected IDetailedTransactionRepository detailedTransactionRepository;

    @InjectMocks
    protected TransactionService transactionService;

    @Test
    public void getCostumerDetailedTransactions_success() {
        
        // Arrange
        int costumerId = 1;
        List<DetailedTransactionDto> transactions = TransactionFactory.buildDetailedTransactionDtos4Test(costumerId, 4);
        when(detailedTransactionRepository.findByCostumerId(costumerId))
        .thenReturn(transactions);
        transactionService.mapper = new ModelMapper(); 

        // Act
        List<Transaction> result = (List<Transaction>)transactionService.getCostumerDetailedTransactions(costumerId);
        assertThat(result).isNotEmpty();
        
        assertThat(result.get(0).getCostumerAccount().getName()).startsWith("Costumer_Account");
        assertThat(result.get(0).getTargetAccount().getCostumer().getName()).startsWith("TC");
    }
}
