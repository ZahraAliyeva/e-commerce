package com.company.ecommerce.repo;

import com.company.ecommerce.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {
    @Modifying
    @Query("DELETE FROM Category p WHERE p.id = :id")
    void deleteCategoryById(@Param("id") Long id);
}
