package ru.geekbrains.spring.product.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.geekbrains.spring.product.model.dtos.ProductDto;
import ru.geekbrains.spring.product.model.entities.Product;
import ru.geekbrains.spring.product.services.ProductService;
import ru.geekbrains.spring.product.utils.ValuesAllowed;

import org.hibernate.exception.ConstraintViolationException;
import java.util.List;

@Validated
@RestController
@RequestMapping("/api/v1/products")
public class ProductControllerV1 {

    @Autowired
    private ProductService productService;


    //1. Добавить пагинацию (с обработкой неправильных значений т.е. возвращать не пустой массив).
    //2. Сделать сортировку по цене (в обе стороны).
    //3*. Сделать сортировку и по цене, и по имени (на основе входящих параметров в обе стороны). + обработка исключений
    // 4**. Сделать выбор приоритета сортировки на основе параметров запроса.
    @GetMapping
    public Page<Product> getAll(@RequestParam(defaultValue = "1") Integer page,
                                @RequestParam(defaultValue = "10") Integer size,
                                @RequestParam(defaultValue = "ASC") Sort.Direction direction,
                                @RequestParam(defaultValue = "cost, name") @ValuesAllowed(values = {"name", "cost"}) String... sortNames) {
        Page<Product> products = productService.getAllPage(page-1, size, direction, sortNames);
        if(!products.hasContent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    String.format("Total page = [%d,] total element = [%d]",
                    products.getTotalPages(),
                            products.getTotalElements()));
        }
        return products;
    }

    //4.** Добавить поиск товара по имени (причем не по полному имени, а хотя бы по кусочку.
    // Например, в качестве параметра поиска передается строка "тел", если есть товары
    // "телевизор" и "телефон" возращаются они все). В случае если товары не найдены
    // вернуть пустой массив. Поиск должен работать независимо от регистра.
    @GetMapping("/name")
    public List<Product>getByName(@RequestParam String name){
        return productService.getByName(name);
    }
    //    получение товара по id [ GET .../geek/api/v1/{id} ]
    @GetMapping("/{id}")
    public Product getById(@PathVariable Long id) {
        return productService.getById(id);
    }

    //    создание нового товара [ POST .../geek/pi/v1/products ]
    @PostMapping
    public Product add(@RequestBody ProductDto product) {
        return productService.save(product);
    }

    //    удаление товара по id.[ GET .../geek/pi/v1/products/{id} ]
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        productService.deleteById(id);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String handleConstraintViolationException(ConstraintViolationException e) {
        return "Validation error: " + e.getMessage();
    }

    @ExceptionHandler(ResponseStatusException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String handleResponseStatusExceptionException(ResponseStatusException e) {
        return "Validation error: " + e.getMessage();
    }

}
