package com.harvest.testools.factories;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.harvest.core.entities.Costumer;

public class CostumerFactory {

    public static Costumer buildCostumer(int id) {
        Costumer c = new Costumer();
        c.setId(id);
        return c;
    }

    public static Collection<Costumer> buildCostumers4Test(int count) {
        List<Costumer> list = new ArrayList<Costumer>();
        for(int i = 0; i < count; i++) {
            list.add(buildCostumer(i + 1));
        }
        return list;        
    }
}
