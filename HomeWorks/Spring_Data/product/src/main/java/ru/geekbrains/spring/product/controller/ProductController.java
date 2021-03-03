package ru.geekbrains.spring.product.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.spring.product.model.Product;
import ru.geekbrains.spring.product.services.ProductService;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;


    //    получение всех товаров [ GET .../geek/products ] c доп. параметрами
    @GetMapping
    public List<Product> getAll(@RequestParam Optional<String> min,
                                @RequestParam Optional<String> max) {

        int minValue = 0;
        if (min.isPresent()) {
            minValue = Integer.parseInt(min.get());
        }
        if (max.isPresent()) {
            return productService.getAll(minValue, Integer.parseInt(max.get()));
        }

        return productService.getAll(minValue);
    }

    //    получение товара по id [ GET .../geek/products/{id} ]
    @GetMapping("/{id}")
    public Product getById(@PathVariable Long id) {
        return productService.getById(id);
    }

    //    создание нового товара [ POST .../geek/products ]
    @PostMapping
    public Product add(@RequestBody Product product) {
        return productService.add(product);
    }

    //    удаление товара по id.[ GET .../geek/products/delete/{id} ]
    @GetMapping("/delete/{id}")
    public void delete(@PathVariable Long id) {
        productService.delete(id);
    }

}
