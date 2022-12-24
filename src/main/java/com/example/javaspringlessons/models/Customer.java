package com.example.javaspringlessons.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;
import org.hibernate.validator.constraints.Length;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Length(min = 3, max = 10, message = "min: 3, max: 10")
    private String name;
    @Length(min = 3, max = 10, message = "min: 3, max: 10")
    private String surname;
    @Email
    private String email;
    private boolean isActivated = false;
    @ToString.Exclude
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private ActivationToken activationToken;
    private String avatar;

    public Customer(String name, String surname, String email) {
        this.name = name;
        this.surname = surname;
        this.email = email;
    }
}
