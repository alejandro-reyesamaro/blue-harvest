package com.harvest.infrastructure.services;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.harvest.application.services.IAccountService;
import com.harvest.application.services.dto.forms.AddAccountForm;
import com.harvest.core.entities.Account;
import com.harvest.infrastructure.repository.account.AccountDto;
import com.harvest.infrastructure.repository.account.IAccountRepository;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService implements IAccountService {
    
    @Autowired
    protected IAccountRepository accountRepository;

    @Autowired
    protected ModelMapper mapper; 

    public Optional<Account> getAccountById(int id) {
        Optional<AccountDto> account = accountRepository.findById(Long.valueOf(id));
        return account.isPresent()
            ? Optional.of(mapper.map(account.get(), Account.class))
            : Optional.empty();
    }

    public Collection<Account> getCostumerAccounts(int id) {
        List<AccountDto> accounts = accountRepository.findAll();
        return accounts.stream().map(c -> mapper.map(c, Account.class)).collect(Collectors.toList());
    }

    public Account createAccount(AddAccountForm form) {
        AccountDto account = accountRepository.save(new AccountDto(form.getCostumerId(), form.getName(), form.getInitialCredit()));
        return mapper.map(account, Account.class);
    }
}
