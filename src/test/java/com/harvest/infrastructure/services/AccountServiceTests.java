package com.harvest.infrastructure.services;

import static org.mockito.Mockito.when;

import java.util.List;

import com.harvest.core.entities.Account;
import com.harvest.infrastructure.repository.account.AccountDto;
import com.harvest.infrastructure.repository.account.IAccountRepository;
import com.harvest.testools.factories.AccountFactory;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class AccountServiceTests {
    
    @Mock
    protected IAccountRepository repository;

    @InjectMocks
    protected AccountService service;

    @Test
    public void getCostumerAccounts_success() {
        // Arrange
        int costumerId = 1;
        List<AccountDto> accounts = AccountFactory.buildAccountsDto4Test(costumerId, 4);
        when(repository.findByCostumerId(costumerId))
        .thenReturn(accounts);
        service.mapper = new ModelMapper();

        // Act
        List<Account> result = (List<Account>)service.getCostumerAccounts(costumerId);

        // Assert
        assertThat(result).isNotEmpty();
        assertThat(result.get(0).getCostumer().getId()).isEqualTo(costumerId);
        assertThat(result.get(0).getId()).isEqualTo(1);
        assertThat(result.get(1).getCostumer().getId()).isEqualTo(costumerId);
        assertThat(result.get(1).getId()).isEqualTo(2);
    }
}
