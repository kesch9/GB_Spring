package com.geekbrains.springboot.repositories;

import com.geekbrains.springboot.model.Product;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.*;

@Component
public class ProductInMemoryRepository {
    private List<Product> products;

    @PostConstruct
    public void init() {
        this.products = new ArrayList<>(Arrays.asList(
                new Product(1L, "Product 1", 25),
                new Product(2L, "Product 2", 15),
                new Product(3L, "Product 3", 25),
                new Product(4L, "Product 4", 22)));
    }

    public Product saveOrUpdate(Product p) {
        if (p.getId() != null) {
            for (int i = 0; i < products.size(); i++) {
                if (products.get(i).getId().equals(p.getId())) {
                    products.set(i, p);
                    return p;
                }
            }
        }

        Long newId = products.stream().mapToLong(Product::getId).max().orElseGet(() -> 0L) + 1L;
        p.setId(newId);
        products.add(p);
        return p;
    }

    public List<Product> findAll() {
        return Collections.unmodifiableList(products);
    }

    public Optional<Product> findById(Long id) {
        return products.stream().filter(p -> p.getId().equals(id)).findFirst();
    }

    public void deleteById(Long id) {
        products.removeIf(p -> p.getId().equals(id));
    }
}