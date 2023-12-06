package com.harvest.api.controllers.strategies.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AccountInfoResponse {
    
    private int id;
    private String name;
    private double balance;
}
