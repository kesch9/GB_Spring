package com.geekbrains.spring.mvc.model;


import lombok.*;

@AllArgsConstructor
@Data
@ToString
public class Product {
    private Long id;
    private String title;
    private int cost;
}
