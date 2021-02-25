package com.geekbrains.hibernate.crud;

import javax.persistence.*;

@Entity
@Table(name = "product_items")
public class ProductItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "cost")
    private int cost;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public ProductItem() {
    }

    public ProductItem(Long id, String title, int cost) {
        this.id = id;
        this.title = title;
        this.cost = cost;
    }

    public ProductItem(String title, int cost) {
        this.title = title;
        this.cost = cost;
    }

    @Override
    public String toString() {
        return String.format("ProductItem [id = %d, title = %s, cost = %d]", id, title, cost);
    }
}

