package com.company.ecommerce.repo;

import com.company.ecommerce.entity.PerProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PerProductRepository extends JpaRepository<PerProduct, Long> {
    void delete(PerProduct perProduct);
    List<PerProduct> findByCode(String code);

    @Modifying
    @Query("DELETE FROM PerProduct p WHERE p.id = :id")
    void deletePerProductById(@Param("id") Long id);

}
