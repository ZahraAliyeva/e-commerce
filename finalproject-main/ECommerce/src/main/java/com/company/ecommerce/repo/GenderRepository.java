package com.company.ecommerce.repo;

import com.company.ecommerce.entity.Gender;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GenderRepository extends JpaRepository<Gender,Long> {

    List<Gender> findByName(String name);
}
