package com.company.ecommerce.repo;

import com.company.ecommerce.entity.ProductSizes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductSizesRepository extends JpaRepository<ProductSizes, Long> {
    @Query(value = "SELECT u.numbers FROM ProductSizes u WHERE u.perProduct.id = ?1 AND u.size.id = ?2")
    Integer findProductCountBySizes(Long perProduct, Long size);

    @Query(value = "SELECT u FROM ProductSizes u WHERE u.perProduct.id = ?1 AND u.size.id = ?2")
    ProductSizes findProductBySizesAndPerProductId(Long perProduct, Long size);

    @Modifying
    @Query("DELETE FROM ProductSizes p WHERE p.id = :id")
    void deleteProductSizesById(@Param("id") Long id);

}
