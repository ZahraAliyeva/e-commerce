package com.company.ecommerce.repo;

import com.company.ecommerce.entity.Size;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SizeRepository extends JpaRepository<Size, Long> {
    List<Size> findBySizeName(String name);
}
