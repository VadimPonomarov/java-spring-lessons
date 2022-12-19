package com.example.javaspringlessons.services;

import com.example.javaspringlessons.dao.CustomerDAO;
import com.example.javaspringlessons.models.ActivationToken;
import com.example.javaspringlessons.models.Customer;
import com.example.javaspringlessons.models.dto.CustomerDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@Service
@Data
@AllArgsConstructor
public class CustomerService {
    private CustomerDAO customerDao;
    private MailService mailService;

    public void setOne(Customer customer) {
        customer.setActivationToken(new ActivationToken());
        System.out.println(customer);
        customerDao.save(customer);
        mailService.send(customer);
    }

    public void activate(String token) {
        Customer candidate = customerDao.getOneByToken(token);
        if(isExpired(candidate.getActivationToken().getExpire())){
            throw new RuntimeException("Token is expired !!!");
        }
        candidate.setActivated(true);
        customerDao.save(candidate);
    }

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


    public void activateCustomer(Customer candidate) {
        customerDao.save(candidate);
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

    public boolean isExpired(LocalDateTime expireDate) {
        return LocalDateTime.now(ZoneId.of("Europe/Kiev")).isAfter(expireDate);
    }

}
