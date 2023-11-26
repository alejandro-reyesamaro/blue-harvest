package com.harvest.core.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Costumer {
    protected int id;
    protected String name;
    protected String surname;

    public Costumer() {}
}
