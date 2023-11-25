package com.harvest.infrastructure.repository.account;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IAccountRepository extends JpaRepository<AccountDto, Long>{
    
}
