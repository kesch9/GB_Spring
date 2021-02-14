package ru.geekbrains.spring.context;


import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@Component("productService")
public class ProductServiceImpl implements ProductService {

    private List<Product> products;

    @PostConstruct
    public void init() {
        products = new ArrayList<>();
        products.add(new Product(0, "Product 1", 100));
        products.add(new Product(1, "Product 2", 200));
    }
    @Override
    public void create(int id, String title, int cost) {
        products.add(new Product(id, title, cost));
    }

    @Override
    public Product read(int id) {
        Product product = null;
        try{
            product = products.get(id);
        }catch (final IndexOutOfBoundsException exp){
            System.out.println("There is not this product");
        }
        return product;
    }

    @Override
    public List<Product> readAll() {
        return products;
    }

    @Override
    public void update(int id, String title, int cost) {
        OptionalInt ind = IntStream.range(0, products.size()).filter(n -> products.get(n).getId() == id).findFirst();
        if (ind.isPresent()){
            products.remove(ind.getAsInt());
            products.add(new Product(id,title,cost));
            return;
        }
        System.out.println("Product didn't find");
    }

    @Override
    public void delete(int id) {

        OptionalInt ind = IntStream.range(0, products.size()).filter(n -> products.get(n).getId() == id).findFirst();

        if (ind.isPresent()){
            products.remove(ind.getAsInt());
            return;
        }
        System.out.println("Product didn't find");
    }

    @Override
    public double averagePrice() {
        return products.stream().mapToInt(x-> x.getCost()).summaryStatistics().getAverage();
    }
}
