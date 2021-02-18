package com.geekbrains.spring.mvc.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class Product {
    private Long id;
    private String title;
    private int cost;
}
