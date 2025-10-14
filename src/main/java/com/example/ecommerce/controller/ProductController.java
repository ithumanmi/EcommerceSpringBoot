package com.example.ecommerce.controller;

import com.example.ecommerce.dto.ApiResponse;
import com.example.ecommerce.dto.ProductDTO;
import com.example.ecommerce.dto.UpdateProductDTO;
import com.example.ecommerce.mapper.ProductMapper;
import com.example.ecommerce.model.Product;
import com.example.ecommerce.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "*")
public class ProductController {
    @Autowired
    private ProductService productService;
    
    @Autowired
    private ProductMapper productMapper;

    @GetMapping
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        return ResponseEntity.ok(productMapper.toDTOList(products));
    }

    @GetMapping("/paginated")
    public ResponseEntity<Page<ProductDTO>> getProductsPaginated(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "ASC") String sortDir
    ) {
        Sort sort = sortDir.equalsIgnoreCase("DESC") 
            ? Sort.by(sortBy).descending() 
            : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Product> productPage = productService.getAllProductsPaginated(pageable);
        return ResponseEntity.ok(productPage.map(productMapper::toDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable Long id) {
        Product product = productService.getProductById(id);
        productService.incrementViewCount(id);
        return ResponseEntity.ok(productMapper.toDTO(product));
    }

    @GetMapping("/sku/{sku}")
    public ResponseEntity<ProductDTO> getProductBySku(@PathVariable String sku) {
        Product product = productService.findBySku(sku);
        return ResponseEntity.ok(productMapper.toDTO(product));
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<ProductDTO>> getProductsByCategory(@PathVariable Long categoryId) {
        List<Product> products = productService.getProductsByCategory(categoryId);
        return ResponseEntity.ok(productMapper.toDTOList(products));
    }

    @GetMapping("/brand/{brand}")
    public ResponseEntity<List<ProductDTO>> getProductsByBrand(@PathVariable String brand) {
        List<Product> products = productService.getProductsByBrand(brand);
        return ResponseEntity.ok(productMapper.toDTOList(products));
    }

    @GetMapping("/active")
    public ResponseEntity<List<ProductDTO>> getActiveProducts() {
        List<Product> products = productService.getActiveProducts();
        return ResponseEntity.ok(productMapper.toDTOList(products));
    }

    @GetMapping("/featured")
    public ResponseEntity<List<ProductDTO>> getFeaturedProducts() {
        List<Product> products = productService.getFeaturedProducts();
        return ResponseEntity.ok(productMapper.toDTOList(products));
    }

    @GetMapping("/search")
    public ResponseEntity<List<ProductDTO>> searchProducts(@RequestParam String keyword) {
        List<Product> products = productService.searchProducts(keyword);
        return ResponseEntity.ok(productMapper.toDTOList(products));
    }

    @GetMapping("/price-range")
    public ResponseEntity<List<ProductDTO>> getProductsByPriceRange(
            @RequestParam BigDecimal minPrice,
            @RequestParam BigDecimal maxPrice
    ) {
        List<Product> products = productService.getProductsByPriceRange(minPrice, maxPrice);
        return ResponseEntity.ok(productMapper.toDTOList(products));
    }

    @GetMapping("/low-stock")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<ProductDTO>> getLowStockProducts(@RequestParam(defaultValue = "10") Integer threshold) {
        List<Product> products = productService.getLowStockProducts(threshold);
        return ResponseEntity.ok(productMapper.toDTOList(products));
    }

    @GetMapping("/out-of-stock")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<ProductDTO>> getOutOfStockProducts() {
        List<Product> products = productService.getOutOfStockProducts();
        return ResponseEntity.ok(productMapper.toDTOList(products));
    }

    @GetMapping("/top-selling")
    public ResponseEntity<List<ProductDTO>> getTopSellingProducts() {
        List<Product> products = productService.getTopSellingProducts();
        return ResponseEntity.ok(productMapper.toDTOList(products));
    }

    @GetMapping("/top-rated")
    public ResponseEntity<List<ProductDTO>> getTopRatedProducts() {
        List<Product> products = productService.getTopRatedProducts();
        return ResponseEntity.ok(productMapper.toDTOList(products));
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ProductDTO> createProduct(@Valid @RequestBody ProductDTO productDTO) {
        Product product = productMapper.toEntity(productDTO);
        Product createdProduct = productService.createProduct(product);
        return ResponseEntity.ok(productMapper.toDTO(createdProduct));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ProductDTO> updateProduct(
            @PathVariable Long id,
            @Valid @RequestBody UpdateProductDTO updateProductDTO
    ) {
        Product updatedProduct = productService.updateProduct(id, updateProductDTO);
        return ResponseEntity.ok(productMapper.toDTO(updatedProduct));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok(new ApiResponse(true, "Product deleted successfully"));
    }

    @PutMapping("/{id}/stock")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse> updateStock(
            @PathVariable Long id,
            @RequestParam Integer quantity
    ) {
        productService.updateStock(id, quantity);
        return ResponseEntity.ok(new ApiResponse(true, "Stock updated successfully"));
    }

    @PutMapping("/{id}/stock/increase")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse> increaseStock(
            @PathVariable Long id,
            @RequestParam Integer quantity
    ) {
        productService.increaseStock(id, quantity);
        return ResponseEntity.ok(new ApiResponse(true, "Stock increased successfully"));
    }

    @PutMapping("/{id}/stock/decrease")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse> decreaseStock(
            @PathVariable Long id,
            @RequestParam Integer quantity
    ) {
        productService.decreaseStock(id, quantity);
        return ResponseEntity.ok(new ApiResponse(true, "Stock decreased successfully"));
    }

    @PutMapping("/{id}/activate")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse> activateProduct(@PathVariable Long id) {
        productService.activateProduct(id);
        return ResponseEntity.ok(new ApiResponse(true, "Product activated successfully"));
    }

    @PutMapping("/{id}/deactivate")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse> deactivateProduct(@PathVariable Long id) {
        productService.deactivateProduct(id);
        return ResponseEntity.ok(new ApiResponse(true, "Product deactivated successfully"));
    }

    @PutMapping("/{id}/toggle-featured")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse> toggleFeatured(@PathVariable Long id) {
        productService.toggleFeatured(id);
        return ResponseEntity.ok(new ApiResponse(true, "Featured status toggled successfully"));
    }

    @GetMapping("/statistics")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Map<String, Object>> getStatistics() {
        Map<String, Object> stats = new HashMap<>();
        stats.put("totalProducts", productService.getTotalProductsCount());
        stats.put("activeProducts", productService.getActiveProductsCount());
        stats.put("outOfStockProducts", productService.getOutOfStockCount());
        return ResponseEntity.ok(stats);
    }
}
