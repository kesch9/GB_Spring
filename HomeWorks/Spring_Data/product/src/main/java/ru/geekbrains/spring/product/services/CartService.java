package ru.geekbrains.spring.product.services;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;
import ru.geekbrains.spring.product.model.Cart;
import ru.geekbrains.spring.product.model.dtos.ProductDto;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CartService {

    @Bean
    @SessionScope
    public Cart sessionScopedBean() {
        return new Cart();
    }

    @Resource(name = "sessionScopedBean")
    Cart cart;

    public void addProduct(ProductDto productDto) {
        cart.add(productDto);
    }

    public void deleteProduct(ProductDto productDto) {
        cart.delete(productDto);
    }

    public List<ProductDto> getCart() {
        return cart.getProducts();
    }
}
