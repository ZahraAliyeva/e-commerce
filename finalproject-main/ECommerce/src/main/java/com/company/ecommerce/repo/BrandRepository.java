package com.company.ecommerce.repo;

import com.company.ecommerce.entity.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BrandRepository extends JpaRepository<Brand,Long> {
    List<Brand> findByBrandName(String brandName);

    @Modifying
    @Query("DELETE FROM Brand p WHERE p.brandId = :id")
    void deleteBrandById(@Param("id") Long id);
}
