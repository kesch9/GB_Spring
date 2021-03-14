package ru.geekbrains.spring.product.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.spring.product.exceptions.ResourceNotFoundException;
import ru.geekbrains.spring.product.model.dtos.ProductDto;
import ru.geekbrains.spring.product.services.CartService;
import ru.geekbrains.spring.product.services.ProductService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/carts")
public class CartController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CartService cartService;

    @GetMapping
    public List<ProductDto> getProductsInCart(){
        return cartService.getCart();
    }

    @GetMapping("/add/{id}")
    public void addToCart(@PathVariable Long id) {
        ProductDto productDto = productService.findProductDtoById(id).
                orElseThrow(() -> new ResourceNotFoundException(
                        "Product with id: "
                        + id +
                        " doesn't exist"));
        cartService.addProduct(productDto);
    }

    @GetMapping("/delete/{id}")
    public void deleteFromCart(@PathVariable Long id) {
        ProductDto productDto = productService.findProductDtoById(id).
                orElseThrow(() -> new ResourceNotFoundException(
                        "Product with id: "
                                + id +
                                " doesn't exist"));
        cartService.deleteProduct(productDto);
    }

}
