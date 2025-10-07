package com.example.ecommerce.mapper;

import com.example.ecommerce.dto.ProductDTO;
import com.example.ecommerce.model.Product;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Mapper class for Product entity and DTO conversion
 */
@Component
public class ProductMapper {

    /**
     * Convert Product entity to ProductDTO
     */
    public ProductDTO toDTO(Product product) {
        if (product == null) {
            return null;
        }

        ProductDTO dto = new ProductDTO();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setDescription(product.getDescription());
        dto.setPrice(product.getPrice());
        dto.setStock(product.getStock());
        dto.setCategoryId(product.getCategoryId());
        
        return dto;
    }

    /**
     * Convert ProductDTO to Product entity
     */
    public Product toEntity(ProductDTO dto) {
        if (dto == null) {
            return null;
        }

        Product product = new Product();
        product.setId(dto.getId());
        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        product.setStock(dto.getStock());
        product.setCategoryId(dto.getCategoryId());
        
        return product;
    }

    /**
     * Convert list of Product entities to list of ProductDTOs
     */
    public List<ProductDTO> toDTOList(List<Product> products) {
        if (products == null) {
            return null;
        }
        
        return products.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * Convert list of ProductDTOs to list of Product entities
     */
    public List<Product> toEntityList(List<ProductDTO> dtos) {
        if (dtos == null) {
            return null;
        }
        
        return dtos.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }
}
