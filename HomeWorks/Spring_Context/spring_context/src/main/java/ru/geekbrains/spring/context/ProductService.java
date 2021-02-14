package ru.geekbrains.spring.context;

import java.util.List;

public interface ProductService {

    void create(int id, String title, int cost);

    Product read(int id);

    List<Product> readAll();

    void update(int id, String title, int cost);

    void delete(int id);

    double averagePrice();

}
