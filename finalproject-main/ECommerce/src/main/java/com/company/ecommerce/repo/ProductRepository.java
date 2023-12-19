package com.company.ecommerce.repo;

import com.company.ecommerce.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query(value = "SELECT u FROM Product u WHERE u.sub_categories.id = ?1")
    Collection<Product> findAllActiveUsersNative(Long categoryId);


    @Query(value = "SELECT u FROM Product u WHERE u.brand.brandId = ?1")
    Collection<Product> findProductsByBrandId(Long categoryId);

    List<Product> findByProductName(String name);

    @Modifying
    @Query("DELETE FROM Product p WHERE p.id = :id")
    void deleteProductById(@Param("id") Long id);

}