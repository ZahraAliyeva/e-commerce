package com.company.ecommerce.repo;

import com.company.ecommerce.entity.Photo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PhotoRepository extends JpaRepository<Photo, Long> {
    @Modifying
    @Query("DELETE FROM Photo p WHERE p.id = :id")
    void deletePhotoById(@Param("id") Long id);

}
