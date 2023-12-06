package com.harvest.infrastructure.repository.transaction;

import java.util.List;

import com.harvest.infrastructure.repository.ReadOnlyRepository;

public interface IDetailedTransactionRepository extends ReadOnlyRepository<DetailedTransactionDto, Long> {
    List<DetailedTransactionDto> findByCostumerId(int id);
}
