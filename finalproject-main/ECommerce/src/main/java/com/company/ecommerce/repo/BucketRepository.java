package com.company.ecommerce.repo;

import com.company.ecommerce.entity.Bucket;
import com.company.ecommerce.entity.PerProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BucketRepository extends JpaRepository<Bucket, Long> {
    @Query(value = "SELECT u FROM Bucket u WHERE u.customer.id = ?1")
    List<Bucket> findBucketProductsByCustomerId(Long customerId);

    @Modifying
    @Query("DELETE FROM Bucket p WHERE p.perProduct.id = :id")
    void deleteProductBucketById(@Param("id") Long id);

    @Query(value = "SELECT u FROM Bucket u WHERE u.customer.id = ?1 AND u.perProduct.id = ?2 AND u.size.id = ?3")
    Bucket findProduct(Long customerId, Long perProductId, Long sizeId);

    @Modifying
    @Query("DELETE FROM Bucket p WHERE p.id = :id")
    void deleteBucketById(@Param("id") Long id);
}
