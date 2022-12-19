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
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @ToString.Exclude
    private ActivationToken activationToken;

}
