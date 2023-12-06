package com.harvest.infrastructure.services;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.harvest.application.services.ITransactionService;
import com.harvest.application.services.dto.forms.AddTransactionForm;
import com.harvest.core.entities.Transaction;
import com.harvest.infrastructure.repository.transaction.DetailedTransactionDto;
import com.harvest.infrastructure.repository.transaction.IDetailedTransactionRepository;
import com.harvest.infrastructure.repository.transaction.ITransactionRepository;
import com.harvest.infrastructure.repository.transaction.TransactionDto;
import com.harvest.infrastructure.services.mappers.DetailedTransactionMapper;
import com.harvest.infrastructure.services.mappers.TransactionMapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionService implements ITransactionService {
    
    @Autowired
    protected ITransactionRepository transactionRepository;

    @Autowired
    protected IDetailedTransactionRepository detailedTransactionRepository;

    @Autowired
    protected ModelMapper mapper;

    @Autowired
    protected TransactionMapper tMapper;

    @Autowired
    protected DetailedTransactionMapper dtMapper;

    public Optional<Transaction> getTransactionById(int id) {
        Optional<TransactionDto> transaction = transactionRepository.findById(Long.valueOf(id));
        return transaction.isPresent()
            ? Optional.of(mapper.map(transaction.get(), Transaction.class))
            : Optional.empty();
    }

    public Collection<Transaction> getCostumerTransactions(int costumerId) {
        List<TransactionDto> transactions = transactionRepository.findByCostumerId(costumerId);
        return transactions.stream().map(c -> tMapper.mapFrom(c)).collect(Collectors.toList());
    }

    public Collection<Transaction> getCostumerDetailedTransactions(int costumerId) {
        List<DetailedTransactionDto> transactions = detailedTransactionRepository.findByCostumerId(costumerId);
        return transactions.stream().map(c -> dtMapper.mapFrom(c)).collect(Collectors.toList());
    }

    public Transaction addTransaction(AddTransactionForm form) {
        TransactionDto transaction = transactionRepository.save(new TransactionDto(
            form.getCostumerId(),
            form.getCostumerAccountId(), 
            form.getTargetAccountId(),
            form.getAmount()));
        return tMapper.mapFrom(transaction);
    }
}
