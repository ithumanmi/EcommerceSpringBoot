package com.example.ecommerce.service;

import com.example.ecommerce.model.Category;
import java.util.List;

public interface CategoryService {
    List<Category> getAllCategories();
    Category getCategoryById(Long id);
    Category createCategory(Category category);
} 
