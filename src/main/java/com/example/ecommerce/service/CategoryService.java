package com.example.ecommerce.service;

import com.example.ecommerce.model.Category;
import org.springframework.lang.NonNull;

import java.util.List;

public interface CategoryService {
    List<Category> getAllCategories();
    Category getCategoryById(@NonNull Long id);
    Category createCategory(Category category);
    Category updateCategory(@NonNull Long id, Category category);
    boolean deleteCategory(@NonNull Long id);
} 
