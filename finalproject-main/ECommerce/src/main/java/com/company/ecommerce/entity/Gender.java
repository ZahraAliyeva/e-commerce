package com.company.ecommerce.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@JsonIgnoreProperties("categories")
public class Gender {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    private String name;

    @ManyToMany(mappedBy = "genders",cascade = CascadeType.ALL)
    private List<Sub_category> categories;


    @OneToMany(mappedBy = "gender",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Product> products;


    public Gender(String name) {
        this.name=name;
    }
}
