package com.company.ecommerce.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Entity
@Data
@Table(name = "sizes")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Size {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "size_name")
    private String sizeName;

    @JsonIgnore
    @OneToOne(mappedBy = "size", cascade = CascadeType.ALL)
    private Bucket bucket;

    @JsonIgnore
    @OneToMany(mappedBy ="size",fetch =FetchType.EAGER ,cascade = CascadeType.ALL)
    private List<ProductSizes> productSizes;

    public Size(String sizeName) {
        this.sizeName=sizeName;
    }
}
