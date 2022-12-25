package com.example.javaspringlessons.services;

import com.example.javaspringlessons.dao.CustomerDAO;
import com.example.javaspringlessons.models.ActivationToken;
import com.example.javaspringlessons.models.Customer;
import com.example.javaspringlessons.models.dto.CustomerDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

@Service
@Data
@AllArgsConstructor
public class CustomerService {
    private CustomerDAO customerDao;
    private MailService mailService;

    public void create(Customer customer) {
        customer.setActivationToken(new ActivationToken());
        customerDao.save(customer);
        mailService.send(customer);
    }

    public void setAvatar(int id, MultipartFile file) throws IOException {
        String path = System.getProperty("user.dir") + File.separator + "images" + File.separator + file.getOriginalFilename();
        File newFile = new File(path);
        file.transferTo(newFile);
        Customer customer = customerDao.findById(id).get();
        customer.setAvatar("/img/" + file.getOriginalFilename());
        customerDao.save(customer);
    }

    public void activate(String token) {
        Customer candidate = customerDao.getOneByToken(token);
        if (isExpired(candidate.getActivationToken().getExpire())) {
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
