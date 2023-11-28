package com.harvest.infrastructure.repository.transaction;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ITransactionRepository extends JpaRepository<TransactionDto, Long>{
    List<TransactionDto> findByCostumerId(int id);
}
