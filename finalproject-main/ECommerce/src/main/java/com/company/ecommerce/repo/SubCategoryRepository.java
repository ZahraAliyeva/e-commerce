package com.company.ecommerce.repo;

import com.company.ecommerce.entity.Sub_category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubCategoryRepository extends JpaRepository<Sub_category,Long> {
    List<Sub_category> findByName(String name);

//    List<Sub_category> findByGenders(List<Gender> genders);

    @Modifying
    @Query("DELETE FROM Sub_category p WHERE p.id = :id")
    void deleteSub_categoriesById(@Param("id") Long id);
}
