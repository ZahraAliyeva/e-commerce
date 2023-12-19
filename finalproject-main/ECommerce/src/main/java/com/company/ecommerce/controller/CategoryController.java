package com.company.ecommerce.controller;

import com.company.ecommerce.entity.Category;
import com.company.ecommerce.entity.Gender;
import com.company.ecommerce.entity.Sub_category;
import com.company.ecommerce.repo.CategoryRepository;
import com.company.ecommerce.repo.GenderRepository;
import com.company.ecommerce.service.CategoryServiceImpl;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/categories",method = RequestMethod.GET)
//@CrossOrigin(origins = "http://localhost:3000")
public class CategoryController {
    private final CategoryServiceImpl categoryService;

    private final GenderRepository genderRepository;
    private final CategoryRepository categoryRepository;


    @PersistenceContext
    private  EntityManager em;


    public CategoryController(CategoryServiceImpl categoryService, GenderRepository genderRepository, CategoryRepository categoryRepository, EntityManager em) {
        this.categoryService = categoryService;
        this.genderRepository = genderRepository;
        this.categoryRepository = categoryRepository;
        this.em = em;
    }


    @PostMapping(path = "/save-category")
    @ResponseStatus(HttpStatus.CREATED)
    @Transactional
    public ResponseEntity<?> createCategory(@RequestBody Category category) {
        Gender existingGender = null;
        List<Gender> existingGenders = new ArrayList<>();
        for (Sub_category sub_category : category.getSub_categories()) {
            for (Gender gender : sub_category.getGenders()) {
                List<Gender> genders = genderRepository.findByName(gender.getName());
                if (!genders.isEmpty()) {
                    existingGender = genders.get(0);
                    if (!existingGenders.contains(existingGender)) {
                        existingGenders.add(existingGender);
                    }
                } else {
                    existingGender = em.merge(gender);
                    existingGenders.add(existingGender);
                }
            }
            if(sub_category.getCategory()==null){
                sub_category.setGenders(existingGenders);
            }
            sub_category.setCategory(category);
        }
        Category userResponse = em.merge(category);
        return new ResponseEntity<>(userResponse, HttpStatus.CREATED);
    }
    @PutMapping("/update-category/{id}")
    public ResponseEntity<?> updateCategory(@RequestBody Category category, @PathVariable("id") Long id){
        Category userResponse = categoryService.updateCategory(category,id);
        return new ResponseEntity<>(userResponse, HttpStatus.OK);
    }

    @DeleteMapping("/delete-category/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable("id") Long id){
        categoryService.deleteCategory(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<?> getCategories(){
        List<Category> categories = categoryService.getCategories();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCategoryById(@PathVariable("id") Long id){
        Category category = categoryService.getCategoryById(id);
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

}
