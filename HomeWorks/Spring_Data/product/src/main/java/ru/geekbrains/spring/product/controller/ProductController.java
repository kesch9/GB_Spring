package ru.geekbrains.spring.product.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.geekbrains.spring.product.model.Product;
import ru.geekbrains.spring.product.services.ProductService;
import ru.geekbrains.spring.product.utils.ValuesAllowed;

import javax.validation.ConstraintViolationException;
import java.util.List;
@Validated
@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;


    //    получение всех товаров [ GET .../geek/products ] c доп. параметрами
//    @GetMapping
//    public List<Product> getAll(@RequestParam Optional<String> min,
//                                @RequestParam Optional<String> max) {
//
//        int minValue = 0;
//        if (min.isPresent()) {
//            minValue = Integer.parseInt(min.get());
//        }
//        if (max.isPresent()) {
//            return productService.getAll(minValue, Integer.parseInt(max.get()));
//        }
//
//        return productService.getAll(minValue);
//    }


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
