package com.example.ecommerce.controller;

import com.example.ecommerce.dto.ApiResponse;
import com.example.ecommerce.dto.ProductDTO;
import com.example.ecommerce.mapper.ProductMapper;
import com.example.ecommerce.model.Product;
import com.example.ecommerce.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "*")
public class ProductController {
    @Autowired
    private ProductService productService;
    
    @Autowired
    private ProductMapper productMapper;

    @GetMapping
    public ResponseEntity<ApiResponse<List<ProductDTO>>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        List<ProductDTO> productDTOs = productMapper.toDTOList(products);
        return ResponseEntity.ok(ApiResponse.success("Products retrieved successfully", productDTOs));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ProductDTO>> getProductById(@PathVariable Long id) {
        Product product = productService.getProductById(id);
        if (product != null) {
            ProductDTO productDTO = productMapper.toDTO(product);
            return ResponseEntity.ok(ApiResponse.success("Product retrieved successfully", productDTO));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ApiResponse.notFound("Product not found with id: " + id));
        }
    }

    @PostMapping
    public ResponseEntity<ApiResponse<ProductDTO>> createProduct(@Valid @RequestBody ProductDTO productDTO) {
        Product product = productMapper.toEntity(productDTO);
        Product createdProduct = productService.createProduct(product);
        ProductDTO createdProductDTO = productMapper.toDTO(createdProduct);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.created(createdProductDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ProductDTO>> updateProduct(@PathVariable Long id, 
                                                                @Valid @RequestBody ProductDTO productDTO) {
        productDTO.setId(id); // Ensure the ID is set
        Product product = productMapper.toEntity(productDTO);
        Product updatedProduct = productService.updateProduct(id, product);
        if (updatedProduct != null) {
            ProductDTO updatedProductDTO = productMapper.toDTO(updatedProduct);
            return ResponseEntity.ok(ApiResponse.updated(updatedProductDTO));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ApiResponse.notFound("Product not found with id: " + id));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteProduct(@PathVariable Long id) {
        boolean deleted = productService.deleteProduct(id);
        if (deleted) {
            return ResponseEntity.ok(ApiResponse.deleted());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ApiResponse.notFound("Product not found with id: " + id));
        }
    }
}
