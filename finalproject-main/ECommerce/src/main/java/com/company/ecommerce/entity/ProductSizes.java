package com.company.ecommerce.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductSizes {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "sizeId", referencedColumnName = "id")
    private Size size;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
//    @Cascade(org.hibernate.annotations.CascadeType.DELETE_ORPHAN)
    @JoinColumn(name = "perProductId", referencedColumnName = "id")
    private PerProduct perProduct;

    private Integer numbers;

}
