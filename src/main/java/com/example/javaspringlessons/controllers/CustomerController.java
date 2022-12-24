package com.example.javaspringlessons.controllers;

import com.example.javaspringlessons.models.Customer;
import com.example.javaspringlessons.models.dto.CustomerDto;
import com.example.javaspringlessons.services.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/customers")
public class CustomerController {
    private CustomerService customerService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public void create(@PathVariable Customer customer) {
        customerService.create(customer);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/{id}/avatar")
    public void setAvatar(@PathVariable int id, @RequestBody MultipartFile file) throws IOException {
        customerService.setAvatar(id, file);
    }

    @GetMapping("")
    public ResponseEntity<List<Customer>> getAll() {
        return new ResponseEntity<>(customerService.getAll(), HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/activate/{token}")
    public void activate(@PathVariable String token) {
        customerService.activate(token);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getOne(@PathVariable int id) {
        return new ResponseEntity<>(customerService.getOneById(id), HttpStatus.OK);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<List<Customer>> getAllByName(@PathVariable String name) {
        return new ResponseEntity<>(customerService.getAllByName(name), HttpStatus.OK);
    }

    @GetMapping("/surname/{surname}")
    public ResponseEntity<List<Customer>> getAllBySurname(@PathVariable String surname) {
        return new ResponseEntity<>(customerService.getAllBySurname(surname), HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<List<Customer>> alterCustomer(@PathVariable int id, @RequestBody CustomerDto data) {
        return new ResponseEntity<>(customerService.updateOneById(id, data), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<List<Customer>> replaceCustomer(@PathVariable int id, @RequestBody Customer customer) {
        return new ResponseEntity<>(customerService.replaceOneById(id, customer), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<List<Customer>> deleteCustomer(@PathVariable int id) {
        return new ResponseEntity<>(customerService.deleteOneById(id), HttpStatus.OK);
    }
}
