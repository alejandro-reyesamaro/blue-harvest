package com.harvest.testools.factories;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.harvest.core.entities.Account;
import com.harvest.infrastructure.repository.account.AccountDto;

public class AccountFactory {

    public static Account buildAccount(int id, int costumerId) {
        return buildAccount(id, costumerId, 0);
    }
    
    public static Account buildAccount(int id, int costumerId, double balance) {
        Account account = new Account();
        account.setName("Default_" + id);
        account.setCostumer(CostumerFactory.buildCostumer(costumerId));
        account.setId(id);
        account.setBalance(balance);
        return account;
    }

    public static AccountDto buildAccountDto(int id, int costumerId, double balance) {
        AccountDto account = new AccountDto(costumerId, "Default_" + id, balance);
        account.setId(id);
        return account;
    }

    public static List<AccountDto> buildAccountsDto4Test(int costumerId, int count) {
        List<AccountDto> list = new ArrayList<AccountDto>();
        for(int i = 0; i < count; i++) {
            list.add(buildAccountDto(i + 1, costumerId, (i+1) * 100));
        }
        return list;
    }

    public static Collection<Account> buildAccounts4Test(int costumerId, int count) {
        List<Account> list = new ArrayList<Account>();
        for(int i = 0; i < count; i++) {
            list.add(buildAccount(i + 1, costumerId));
        }
        return list;
    }
}
