package com.geekbrains.hiber.services;

import com.geekbrains.hiber.entities.Product;
import com.geekbrains.hiber.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    @Autowired
    CustomerRepository repo;
    public List<Product> getProducts(long id){
        return repo.readProducts(id);
    }
}
