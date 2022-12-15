package com.example.javaspringlessons.controllers;

import com.example.javaspringlessons.models.Customer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class MainController {
    List<Customer> customers = new ArrayList<>();

    public MainController() {

        customers.add(new Customer(1, "Name1", "Surname1"));
        customers.add(new Customer(2, "Name2", "Surname2"));
        customers.add(new Customer(3, "Name3", "Surname3"));
    }

    @PostMapping("/customer")
    public ResponseEntity<List<Customer>> setCustomer(@RequestBody Customer customer) {
        customers.add(customer);
        return new ResponseEntity<>(customers, HttpStatus.CREATED);
    }

    @GetMapping("/customers")
    public ResponseEntity<List<Customer>> getCustomers() {
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @PutMapping("/customer/{id}")
    public ResponseEntity<List<Customer>> replaceCustomer(@PathVariable int id, @RequestBody Customer customer) {
        customers =
                customers
                        .stream()
                        .map(item ->
                                item.getId() == id ?
                                        customer
                                        : item
                        )
                        .toList();

        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @PatchMapping("/customer/{id}")
    public ResponseEntity<List<Customer>> alterCustomer(@PathVariable int id, @RequestBody Customer customer) {
        customer =
                customers
                        .stream()
                        .filter(item ->
                                item.getId() == id
                        )
                        .findFirst()
                        .get();
        customers.set(customers.indexOf(customer), customer);
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @DeleteMapping("/customer/{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable int id) {
        customers = customers.stream().filter(item -> item.getId() != id).toList();
        return new ResponseEntity<>("Id: " + id + " is deleted !", HttpStatus.OK);
    }


}
