package com.company.ecommerce.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Sub_category {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;
    private String name;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)

    @JoinColumn(name = "categoryId", referencedColumnName = "id")
    private Category category;

    @ManyToMany(cascade = { CascadeType.ALL},fetch = FetchType.EAGER)
    @JoinTable(
            name = "sub_category_gender",
            joinColumns = { @JoinColumn(name = "sub_categoryId") },
            inverseJoinColumns = { @JoinColumn(name = "genderId") }
    )
    private List<Gender> genders;

    public Sub_category(String name, Category category) {
        this.name = name;
        this.category = category;
    }

    @JsonIgnore
//    @Cascade(org.hibernate.annotations.CascadeType.DELETE_ORPHAN)
    @OneToMany(mappedBy = "sub_categories",fetch = FetchType.EAGER)
    private List<Product> products;

//    public List<Gender> getGenders() {
//        Hibernate.initialize(genders);
//        return genders;
//    }


}