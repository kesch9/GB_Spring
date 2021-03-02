package com.geekbrains.hiber.entities;
import lombok.*;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NonNull
    @Column(name = "title")
    private String title;

    @NonNull
    @Column(name = "cost")
    private int cost;

    @ManyToMany
    @JoinTable(
            name = "products_customers",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "customer_id")
    )
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Customer> customers;

    @OneToMany(mappedBy = "product")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<ProductCustomer> productCustomers = new ArrayList<>();

    @Override
    public String toString() {
        return String.format("Product [id = %d, title = %s, cost=%d]", id, title, cost);
    }

}


