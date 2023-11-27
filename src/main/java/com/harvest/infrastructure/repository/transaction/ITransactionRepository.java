package com.harvest.infrastructure.repository.transaction;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ITransactionRepository extends JpaRepository<TransactionDto, Long>{
    
}
