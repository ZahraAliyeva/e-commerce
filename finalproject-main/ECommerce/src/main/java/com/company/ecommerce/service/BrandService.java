package com.company.ecommerce.service;

import com.company.ecommerce.entity.Brand;

import java.util.List;

public interface BrandService {
    Brand createBrand(Brand brand);
    Brand updateBrand(Brand brand, Long id);
    void deleteBrand(Long id);
    List<Brand> getBrands();
    Brand getBrandById(Long id);
}
