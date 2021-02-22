package com.geekbrains.springboot.services;

import com.geekbrains.springboot.model.Product;
import com.geekbrains.springboot.repositories.ProductInMemoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductInMemoryRepository productInMemoryRepository;

    public Optional<Product> findById(Long id) {
        return productInMemoryRepository.findById(id);
    }

    public List<Product> findAll() {
        return productInMemoryRepository.findAll();
    }

    public List<Product> findAll(Integer minCost, Integer maxCost) {
        List<Product> out = findAll();
        if (minCost != null) {
            out = out.stream().filter(p -> p.getCost() >= minCost).collect(Collectors.toList());
        }
        if (maxCost != null) {
            out = out.stream().filter(p -> p.getCost() <= maxCost).collect(Collectors.toList());
        }
        return out;
    }

    public void saveOrUpdate(Product product) {
        productInMemoryRepository.saveOrUpdate(product);
    }

    public void deleteById(Long id) {
        productInMemoryRepository.deleteById(id);
    }
}
