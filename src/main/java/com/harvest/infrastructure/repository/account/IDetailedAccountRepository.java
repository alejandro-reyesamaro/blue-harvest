package com.harvest.infrastructure.repository.account;

import java.util.List;

import com.harvest.infrastructure.repository.ReadOnlyRepository;

public interface IDetailedAccountRepository extends ReadOnlyRepository<DetailedAccountDto, Long>{
    List<AccountDto> findByCostumerId(int id);
}
