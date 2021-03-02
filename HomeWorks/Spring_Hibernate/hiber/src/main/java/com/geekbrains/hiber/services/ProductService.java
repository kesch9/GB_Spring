package com.geekbrains.hiber.services;

import com.geekbrains.hiber.entities.Customer;
import com.geekbrains.hiber.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductRepository repo;

    public List<Customer> getCustomers(long id){
        return repo.readCustomers(id);
    }
}
