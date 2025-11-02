package com.example.ecommerce.service.impl;

import com.example.ecommerce.model.Category;
import com.example.ecommerce.repository.CategoryRepository;
import com.example.ecommerce.service.CategoryService;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    @SuppressWarnings("null")
    public @NonNull Category getCategoryById(@NonNull Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found with ID: " + id));
    }
    @Override
    @SuppressWarnings("null")
    public Category createCategory(@NonNull Category category) {
        return categoryRepository.save(category);
    }

    @Override
    @SuppressWarnings("null")
    public Category updateCategory(@NonNull Long id, @NonNull Category category) {
        return categoryRepository.save(category);
    }
    public boolean deleteCategory(@NonNull Long id) {
        categoryRepository.deleteById(id);
        return categoryRepository.existsById(id);
    }
} 
