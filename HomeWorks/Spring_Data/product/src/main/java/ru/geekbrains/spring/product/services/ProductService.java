package ru.geekbrains.spring.product.services;

import org.springframework.beans.factory.annotation.Autowired;
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

//    public List<Student> getAllByScore(Integer first, Integer second) {
//
//        return studentRepository.findStudentsByScoreBetween(first, second);
//    }

    public Product getById(Long id) {
        return productRepository.findById(id).get();
    }

    public Product getByName(String name) {
        return productRepository.findProductByName(name);
    }

    public Product add(Product product) {
        return productRepository.save(product);
    }

    public void delete(Long id) {
        productRepository.deleteById(id);
    }
}
