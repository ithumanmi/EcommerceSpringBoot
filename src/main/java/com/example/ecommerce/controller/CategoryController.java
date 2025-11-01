package com.example.ecommerce.controller;

import com.example.ecommerce.dto.ApiResponse;
import com.example.ecommerce.dto.CategoryDTO;
import com.example.ecommerce.mapper.CategoryMapper;
import com.example.ecommerce.model.Category;
import com.example.ecommerce.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@CrossOrigin(origins = "*")
public class CategoryController {
    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;

    public CategoryController(CategoryService categoryService, CategoryMapper categoryMapper) {
        this.categoryService = categoryService;
        this.categoryMapper = categoryMapper;
    }

    @GetMapping
    public ResponseEntity<List<CategoryDTO>> getAllCategories() {
        List<Category> categories = categoryService.getAllCategories();
        List<CategoryDTO> categoryDTOs = categoryMapper.toDTOList(categories);
        if (categoryDTOs == null) {
            categoryDTOs = new java.util.ArrayList<>();
        }
        return ResponseEntity.ok(categoryDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<CategoryDTO>> getCategoryById(@PathVariable Long id) {
        Category category = categoryService.getCategoryById(id);
        if (category != null) {
            CategoryDTO categoryDTO = categoryMapper.toDTO(category);
            return ResponseEntity.ok(ApiResponse.success("Category retrieved successfully", categoryDTO));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ApiResponse.notFound("Category not found with id: " + id));
        }
    }

    @PostMapping
    public ResponseEntity<ApiResponse<CategoryDTO>> createCategory(@Valid @RequestBody CategoryDTO categoryDTO) {
        Category category = categoryMapper.toEntity(categoryDTO);
        Category createdCategory = categoryService.createCategory(category);
        CategoryDTO createdCategoryDTO = categoryMapper.toDTO(createdCategory);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.created(createdCategoryDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<CategoryDTO>> updateCategory(@PathVariable Long id, 
                                                                  @Valid @RequestBody CategoryDTO categoryDTO) {
        categoryDTO.setId(id); // Ensure the ID is set
        Category category = categoryMapper.toEntity(categoryDTO);
        Category updatedCategory = categoryService.updateCategory(id, category);
        if (updatedCategory != null) {
            CategoryDTO updatedCategoryDTO = categoryMapper.toDTO(updatedCategory);
            return ResponseEntity.ok(ApiResponse.updated(updatedCategoryDTO));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ApiResponse.notFound("Category not found with id: " + id));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteCategory(@PathVariable Long id) {
        boolean deleted = categoryService.deleteCategory(id);
        if (deleted) {
            return ResponseEntity.ok(ApiResponse.deleted());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ApiResponse.notFound("Category not found with id: " + id));
        }
    }
} 
