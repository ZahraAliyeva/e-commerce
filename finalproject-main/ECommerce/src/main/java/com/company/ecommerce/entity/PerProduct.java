package com.company.ecommerce.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    public class PerProduct {
        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE)
        @Column(name = "id", nullable = false)
        private Long id;

        private Double price;

        private String code;

        @JsonManagedReference
        @OneToMany(mappedBy = "perProduct",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
        private List<Photo> photos;

        @JsonIgnore
        @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
//        @Cascade(org.hibernate.annotations.CascadeType.DELETE_ORPHAN)
        @JoinColumn(name = "productId", referencedColumnName = "id")
        private Product product;


    //    @JsonBackReference
        @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
//        @Cascade(org.hibernate.annotations.CascadeType.DELETE_ORPHAN)
        @JoinColumn(name = "colorId", referencedColumnName = "id")
        private Color color;

    //    @Column(name = "stock_number")
    //    private int stockNumbers;
    //    @JsonBackReference


        @JsonManagedReference
        @OneToMany(mappedBy = "perProduct",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
        private List<Rate> rate;

        private int stockNumber;

//        @JsonIgnore
        @OneToMany(mappedBy = "perProduct",fetch =FetchType.EAGER ,cascade = CascadeType.ALL)
        private List<ProductSizes> productSizes;


    //    @JsonIgnore
    //    @ManyToMany(cascade = { CascadeType.ALL},fetch = FetchType.EAGER)
    //    @JoinTable(
    //            name = "per_product_bucket",
    //            joinColumns = { @JoinColumn(name = "perProductId") },
    //            inverseJoinColumns = { @JoinColumn(name = "bucketId") }
    //    )
    //    private List<Bucket> buckets;

        @JsonIgnore
        @OneToOne(mappedBy = "perProduct", cascade = CascadeType.ALL)
        private Bucket bucket;

        @JsonIgnore
        @OneToOne(mappedBy = "perProduct", cascade = CascadeType.ALL)
        private Wishlist wishlist;

    }
