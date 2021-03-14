package ru.geekbrains.spring.product.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.geekbrains.spring.product.model.dtos.ProductDto;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Component
@Scope("session")
public class Cart {

    List<ProductDto> products = new ArrayList<>();

    public void add(ProductDto productDto) {
        products.add(productDto);
    }

    public void delete(ProductDto productDto) {
        products.remove(productDto);
    }



}
