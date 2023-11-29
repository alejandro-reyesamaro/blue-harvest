package com.harvest.testools.factories;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.harvest.core.entities.Account;

public class AccountFactory {

    public static Account buildAccount(int id, int costumerId) {
        return buildAccount(id, costumerId, 0);
    }
    
    public static Account buildAccount(int id, int costumerId, double balance) {
        Account account = new Account();
        account.setCostumerId(costumerId);
        account.setId(id);
        account.setBalance(balance);
        return account;
    }

    public static Collection<Account> getAccounts4Test(int costumerId, int count) {
        List<Account> list = new ArrayList<Account>();
        for(int i = 0; i < count; i++) {
            list.add(buildAccount(i + 1, costumerId));
        }
        return list;
    }
}
