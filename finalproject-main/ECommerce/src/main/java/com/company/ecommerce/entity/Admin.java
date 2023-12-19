package com.company.ecommerce.entity;

import jakarta.persistence.*;
import lombok.Data;



@Entity
@Data
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

//    private Set<Customer> customers;
//
//    private List<Brand> brands;
//
//    private List<Product> products;
}
