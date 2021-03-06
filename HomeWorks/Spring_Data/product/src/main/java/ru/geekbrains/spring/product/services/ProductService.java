package ru.geekbrains.spring.product.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.geekbrains.spring.product.model.Product;
import ru.geekbrains.spring.product.repository.ProductRepository;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAll(int min) {
        return productRepository.findProductsByCostGreaterThan(min);
    }

    public List<Product> getAll(int min, int max) {
        return productRepository.findProductsByCostBetween(min, max);
    }

    public Page<Product> getAllPage(int page, int size, Sort.Direction direction, String... param) {
        return productRepository.findAll(PageRequest.of(page, size, direction, param));
    }

    public Product getById(Long id) {
        return productRepository.findById(id).get();
    }

    public List<Product> getByName(String name) {
        return productRepository.findProductsByNameContainingIgnoreCase(name);
    }

    public Product add(Product product) {
        return productRepository.save(product);
    }

    public void delete(Long id) {
        productRepository.deleteById(id);
    }
}
