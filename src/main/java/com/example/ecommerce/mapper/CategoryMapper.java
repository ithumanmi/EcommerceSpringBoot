package com.example.ecommerce.mapper;

import com.example.ecommerce.dto.CategoryDTO;
import com.example.ecommerce.model.Category;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Mapper class for Category entity and DTO conversion
 */
@Component
public class CategoryMapper {

    /**
     * Convert Category entity to CategoryDTO
     */
    public CategoryDTO toDTO(Category category) {
        if (category == null) {
            return null;
        }

        CategoryDTO dto = new CategoryDTO();
        dto.setId(category.getId());
        dto.setName(category.getName());
        dto.setDescription(category.getDescription());
        
        return dto;
    }

    /**
     * Convert CategoryDTO to Category entity
     */
    public Category toEntity(CategoryDTO dto) {
        if (dto == null) {
            return null;
        }

        Category category = new Category();
        category.setId(dto.getId());
        category.setName(dto.getName());
        category.setDescription(dto.getDescription());
        
        return category;
    }

    /**
     * Convert list of Category entities to list of CategoryDTOs
     */
    public List<CategoryDTO> toDTOList(List<Category> categories) {
        if (categories == null) {
            return null;
        }
        
        return categories.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * Convert list of CategoryDTOs to list of Category entities
     */
    public List<Category> toEntityList(List<CategoryDTO> dtos) {
        if (dtos == null) {
            return null;
        }
        
        return dtos.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }
}
