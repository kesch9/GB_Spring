package ru.geekbrains.spring.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.spring.product.model.Product;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Product findProductByName(String name);

    List<Product> findProductsByCostGreaterThan(int min);

    List<Product> findProductsByCostBetween(int min, int max);
}
