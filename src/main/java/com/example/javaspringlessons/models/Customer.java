package com.example.javaspringlessons.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@NoArgsConstructor
@Data
@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Length(min = 3, max = 10, message = "min: 3, max: 10")
    private String name;
    @Length(min = 3, max = 10, message = "min: 3, max: 10")
    private String surname;

    public Customer(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }
}
