package com.example.javaspringlessons.services;

import com.example.javaspringlessons.dao.CustomerDAO;
import com.example.javaspringlessons.models.Customer;
import com.example.javaspringlessons.models.dto.CustomerDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Data
@AllArgsConstructor
public class CustomerService {
    private CustomerDAO customerDao;

    public List<Customer> getAll() {
        return customerDao.findAll();
    }

    public List<Customer> getAllByName(String name) {
        return getAll()
                .stream()
                .filter(customer -> customer.getName().equals(name))
                .toList();
    }

    public List<Customer> getAllBySurname(String surname) {
        return customerDao.getAllBySurname(surname);
    }

    public Customer getOneById(int id) {
        return customerDao.findById(id).get();
    }

    public List<Customer> setOne(Customer customer) {
        customerDao.save(customer);
        return customerDao.findAll();
    }

    public List<Customer> updateOneById(int id, CustomerDto data) {
        Customer candidate = customerDao.findById(id).get();
        switch (data.getField()) {
            case "name":
                candidate.setName(data.getValue());
                break;
            case "surname":
                candidate.setSurname(data.getValue());
                break;
            default:
                throw new RuntimeException("Check Dto data structure. Should be {\n" +
                        "    private String field;\n" +
                        "    private String value;\n" +
                        "}. Example: {\"field\": \"name\", \"value\": \"newName\"}");
        }
        customerDao.save(candidate);
        return customerDao.findAll();
    }

    public List<Customer> replaceOneById(int id, Customer customer) {
        Customer candidate = customerDao.findById(id).get();
        candidate.setName(customer.getName());
        candidate.setSurname(customer.getSurname());
        customerDao.save(candidate);
        return customerDao.findAll();
    }

    public List<Customer> deleteOneById(int id) {
        customerDao.deleteById(id);
        return customerDao.findAll();
    }

}
