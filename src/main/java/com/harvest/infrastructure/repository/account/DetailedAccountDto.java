package com.harvest.infrastructure.repository.account;

import org.springframework.data.annotation.Immutable;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity(name = "DetailedAccount")
@Immutable
@Data
@AllArgsConstructor
public class DetailedAccountDto {
    
    @Id
    protected int id;
    protected String name;
    protected double balance;
    protected int costumerId;
    protected String costumerName;
    protected String costumerSurname;

    public DetailedAccountDto(){}
}
