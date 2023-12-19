package com.company.ecommerce.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

    @Entity
    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public class Brand {
        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE)
        @Column(name = "brand_id", nullable = false)
        private Long brandId;

        @Column(name = "brand_name")
        private String brandName;

        private String iconLink;

        @JsonIgnore
        @OneToMany(mappedBy = "brand",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
        private List<Product> products;

    }
