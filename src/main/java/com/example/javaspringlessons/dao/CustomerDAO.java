package com.example.javaspringlessons.dao;

import com.example.javaspringlessons.models.ActivationToken;
import com.example.javaspringlessons.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CustomerDAO extends JpaRepository<Customer, Integer> {
    @Query("select c from Customer c where c.surname=:surname")
    List<Customer> getAllBySurname(@Param("surname") String surname);

    @Query("select c from Customer c where c.activationToken.token=:token")
    Customer getOneByToken(@Param("token") String token);

}
