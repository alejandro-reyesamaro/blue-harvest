package com.harvest.infrastructure.repository.account;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IAccountRepository extends JpaRepository<AccountDto, Long>{
    List<AccountDto> findByCostumerId(int id);
}
