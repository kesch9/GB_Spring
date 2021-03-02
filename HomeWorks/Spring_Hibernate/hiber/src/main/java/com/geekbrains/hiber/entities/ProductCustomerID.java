package com.geekbrains.hiber.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class ProductCustomerID implements Serializable {

    @NonNull
    @Column(name = "customer_id")
    Long customerId;

    @NonNull
    @Column(name = "product_id")
    Long productId;

}
