package com.company.ecommerce.service;

import com.company.ecommerce.entity.*;
import com.company.ecommerce.repo.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BrandServiceImpl implements BrandService{


    @Autowired
    BrandRepository brandRepo;
    @Autowired
    private PhotoRepository photoRepository;
    @Autowired
    private ProductSizesRepository productSizesRepository;
    @Autowired
    private RateRepository rateRepository;
    @Autowired
    private PerProductRepository perProductRepository;
    @Autowired
    private ProductRepository productRepository;

    @Override
    public Brand createBrand(Brand brand) {
        Brand savedBrand = brandRepo.save(brand);
        return savedBrand;
    }

    @Override
    public Brand updateBrand(Brand brand, Long id) {
        Brand updatedBrand = new Brand();
        Optional<Brand> savedBrand = brandRepo.findById(id);
        updatedBrand = savedBrand.get();
        if(savedBrand.isPresent()){
            BeanUtils.copyProperties(brand, updatedBrand/*, Utils.getNullPropertyName(product)*/);
        }
        updatedBrand=brandRepo.save(updatedBrand);
        return updatedBrand;
    }

    @Override
    public void deleteBrand(Long id) {
        Optional<Brand> brand = brandRepo.findById(id);
        if(brand.isPresent()){
            Brand deletedBrand = brand.get();
            List<Product> Products = deletedBrand.getProducts();
            for (Product pp:Products) {
                for (PerProduct perProduct : pp.getProducts()) {
                    for (Photo p : perProduct.getPhotos()) {
                        photoRepository.deletePhotoById(p.getId());
                    }
                    for (ProductSizes ps : perProduct.getProductSizes()) {
                        productSizesRepository.deleteProductSizesById(ps.getId());
                    }
                    for (Rate r : perProduct.getRate()) {
                        rateRepository.deleteRateById(r.getId());
                    }
                    perProductRepository.deletePerProductById(perProduct.getId());
                }
                productRepository.deleteProductById(pp.getId());
            }
            brandRepo.deleteBrandById(deletedBrand.getBrandId());
        }
    }

    @Override
    public List<Brand> getBrands() {
        List<Brand> brands = brandRepo.findAll();
        return brands;
    }

    @Override
    public Brand getBrandById(Long id) {
        Brand brand = new Brand();
        Optional<Brand> givenBrand = brandRepo.findById(id);
        return givenBrand.orElse(brand);
    }
}
