package com.harvest.infrastructure.repository.Costumer;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Costumer")
@Data
public class CostumerDto {

    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "Name")
    private String name;

    @Column(name = "Surname")
    private String surname;

    public CostumerDto(){}

    public CostumerDto(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    @Override
    public String toString() {
        return "Costumer [id=" + id + ", Name=" + name + ", Surname=" + surname;
    }
}