package com.company.ecommerce.repo;

import com.company.ecommerce.entity.Color;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ColorRepository extends JpaRepository<Color, Long> {
    List<Color> findByColorName(String name);
}
