package com.harvest.infrastructure.repository.transaction;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IDetailedTransactionRepository extends JpaRepository<DetailedTransactionDto, Long> {
    List<DetailedTransactionDto> findByCostumerId(int id);
}
