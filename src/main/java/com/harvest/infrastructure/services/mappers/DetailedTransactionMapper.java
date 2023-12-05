package com.harvest.infrastructure.services.mappers;

import com.harvest.core.entities.Transaction;
import com.harvest.infrastructure.repository.transaction.DetailedTransactionDto;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DetailedTransactionMapper implements IMapper<Transaction, DetailedTransactionDto> {

    @Autowired
    protected ModelMapper mapper;
    
    @Override
    public Transaction mapFrom(DetailedTransactionDto dto) {
        Transaction transaction = mapper.map(dto, Transaction.class);
        // Repair (set coherent values to non-used fields):
        transaction.getCostumer().setName(null);
        transaction.getCostumerAccount().getCostumer().setId(0);
        transaction.getCostumerAccount().getCostumer().setName(null);
        transaction.getTargetAccount().getCostumer().setId(0);
        return transaction;
    }
}
