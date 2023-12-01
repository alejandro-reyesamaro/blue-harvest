package com.harvest.testools.factories;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.harvest.core.entities.Injection;

public class InjectionFactory {
    
    public static Injection buildInjection(int id, int costumerId, int accountId, double credit) {
        Injection injection = new Injection();
        injection.setId(id);
        injection.setCostumer(CostumerFactory.buildCostumer(costumerId));
        injection.setCostumerAccount(AccountFactory.buildAccount(accountId, costumerId));
        injection.setAmount(credit);
        return injection;
    }

    public static Collection<Injection> buildInjections4Test(int costumerId, int count) {
        List<Injection> list = new ArrayList<Injection>();
        for(int i = 0; i < count; i++) {
            list.add(buildInjection(i + 1, costumerId, 10 + costumerId, 1000 * (i + 1)));
        }
        return list;
    }
}
