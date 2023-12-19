package com.company.ecommerce.repo;

import com.company.ecommerce.entity.Rate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RateRepository extends JpaRepository<Rate, Long> {
    @Modifying
    @Query("DELETE FROM Rate p WHERE p.id = :id")
    void deleteRateById(@Param("id") Long id);

}