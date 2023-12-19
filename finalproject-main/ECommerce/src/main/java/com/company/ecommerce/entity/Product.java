package com.company.ecommerce.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

    @Entity
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public class Product {
        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE)
        @Column(name = "id", nullable = false)
        private Long id;

        @Column(name = "product_name")
        private String productName;

        private String description;


        @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
//        @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
        @JoinColumn(name = "sub_categoryId",referencedColumnName = "id")
        private Sub_category sub_categories;


        @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
//        @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
        @JoinColumn(name = "brand_id", referencedColumnName = "brand_id")
        private Brand brand;

//        @Cascade(org.hibernate.annotations.CascadeType.DELETE_ORPHAN)
        @OneToMany(mappedBy = "product",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
        private List<PerProduct> products;

        @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
        @JoinColumn(name = "gender_id",referencedColumnName = "id")
        private Gender gender;
    }
