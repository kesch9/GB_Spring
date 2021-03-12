package ru.geekbrains.spring.product.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import ru.geekbrains.spring.product.model.entities.Product;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {

    Product findProductByTitle(String name);

    List<Product> findProductsByTitleContainingIgnoreCase(String name);

    List<Product> findProductsByPriceGreaterThan(int min);

    List<Product> findProductsByPriceBetween(int min, int max);
}
