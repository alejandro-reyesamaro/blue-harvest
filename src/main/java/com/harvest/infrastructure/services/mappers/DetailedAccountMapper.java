package com.harvest.infrastructure.services.mappers;

import com.harvest.core.entities.Account;
import com.harvest.infrastructure.repository.account.DetailedAccountDto;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DetailedAccountMapper implements IMapper<Account, DetailedAccountDto> {
    
    @Autowired
    protected ModelMapper mapper;
    
    @Override
    public Account mapFrom(DetailedAccountDto dto) {
        Account account = mapper.map(dto, Account.class);
        // Repair (set coherent values to non-used fields):
        account.getCostumer().setName(dto.getCostumerName());
        account.getCostumer().setSurname(dto.getCostumerSurname());
        return account;
    }
}
