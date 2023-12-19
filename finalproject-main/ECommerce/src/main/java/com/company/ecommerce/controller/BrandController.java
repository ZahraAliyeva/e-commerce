package com.company.ecommerce.controller;

import com.company.ecommerce.entity.Brand;
import com.company.ecommerce.service.BrandServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/brands",method = RequestMethod.GET)
//@CrossOrigin(origins = "http://localhost:3000")
public class BrandController {
    private final BrandServiceImpl brandService;

    public BrandController(BrandServiceImpl brandService) {
        this.brandService = brandService;
    }

    @PostMapping(path = "/save-brand")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> createBrand(@RequestBody Brand brand){
        Brand userResponse = brandService.createBrand(brand);
        return new ResponseEntity<>(userResponse, HttpStatus.CREATED);
    }

    @PutMapping("/update-brand/{id}")
    public ResponseEntity<?> updateBrand(@RequestBody Brand brand, @PathVariable("id") Long id){
        Brand userResponse = brandService.updateBrand(brand,id);
        return new ResponseEntity<>(userResponse, HttpStatus.OK);
    }

    @DeleteMapping("/delete-brand/{id}")
    public ResponseEntity<?> deleteBrand(@PathVariable("id") Long id){
        brandService.deleteBrand(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<?> getBrands(){
        List<Brand> brands = brandService.getBrands();
        return new ResponseEntity<>(brands, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getBrandById(@PathVariable("id") Long id){
        Brand brand = brandService.getBrandById(id);
        return new ResponseEntity<>(brand, HttpStatus.OK);
    }

}
