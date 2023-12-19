package com.company.ecommerce.service;

import com.company.ecommerce.entity.Category;

import java.util.List;

public interface CategoryService {
    Category createCategory(Category category);
    Category updateCategory(Category category, Long id);
    void deleteCategory(Long id);
    List<Category> getCategories();
    Category getCategoryById(Long id);
}
