package com.geekbrains.hiber.entities;

import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "products_customers")
public class ProductCustomer {

    @EmbeddedId
    ProductCustomerID id;

    @ManyToOne
    @MapsId("productId")
    @JoinColumn(name = "product_id")
    Product product;

    @ManyToOne
    @MapsId("customerId")
    @JoinColumn(name = "customer_id")
    Customer customer;

    @Column(name = "cost")
    private Long cost;

}

