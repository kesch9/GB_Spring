package ru.geekbrains.spring.product.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.spring.product.exceptions.ResourceNotFoundException;
import ru.geekbrains.spring.product.model.dtos.ProductDto;
import ru.geekbrains.spring.product.model.entities.Product;
import ru.geekbrains.spring.product.repositories.specifications.ProductSpecifications;
import ru.geekbrains.spring.product.services.ProductService;

@RestController
@RequestMapping("/api/v2/products")
public class ProductControllerV2 {

    @Autowired
    private  ProductService productService;

    @GetMapping
    public Page<ProductDto> findAllProducts(
            @RequestParam MultiValueMap<String, String> params,
            @RequestParam(name = "p", defaultValue = "1") Integer page
    ) {
        if (page < 1) {
            page = 1;
        }
        return productService.findAll(ProductSpecifications.build(params), page, 2);
    }

    // http://localhost:8189/happy/api/v1/products
    @GetMapping("/{id}")
    public ProductDto findProductById(@PathVariable Long id) {
        return productService.findProductDtoById(id).orElseThrow(() -> new ResourceNotFoundException("Product with id: " + id + " doesn't exist"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product saveNewProduct(@RequestBody ProductDto productDto) {
        return productService.save(productDto);
    }

    @PutMapping
    public Product updateProduct(@RequestBody ProductDto productDto) {
        return productService.save(productDto);
    }

    @DeleteMapping("/{id}")
    public void updateProduct(@PathVariable Long id) {
        productService.deleteById(id);
    }
}
