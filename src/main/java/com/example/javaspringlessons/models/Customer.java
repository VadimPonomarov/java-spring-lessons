package com.example.javaspringlessons.models;

import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;
import javax.validation.constraints.Email;

@NoArgsConstructor
@AllArgsConstructor
@ToString
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
    @Email
    private String email;
    private boolean isActivated = false;
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private ActivationToken activationToken;
    private String avatar;
    @Column(unique = true)
    private String login;
    private String password;
    private String role = "ROLE_CLIENT";

    public Customer(String name, String surname, String email, String login, String password) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.login = login;
        this.password = password;
    }
}
