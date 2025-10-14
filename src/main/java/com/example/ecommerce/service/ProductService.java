package com.example.ecommerce.service;

import com.example.ecommerce.dto.UpdateProductDTO;
import com.example.ecommerce.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService {
    List<Product> getAllProducts();
    Product getProductById(Long id);
    Product createProduct(Product product);
    Product updateProduct(Long id, UpdateProductDTO updateProductDTO);
    void deleteProduct(Long id);
    
    Product findBySku(String sku);
    List<Product> getProductsByCategory(Long categoryId);
    List<Product> getProductsByBrand(String brand);
    List<Product> getActiveProducts();
    List<Product> getFeaturedProducts();
    List<Product> searchProducts(String keyword);
    List<Product> getProductsByPriceRange(BigDecimal minPrice, BigDecimal maxPrice);
    
    Page<Product> getAllProductsPaginated(Pageable pageable);
    
    void updateStock(Long productId, Integer quantity);
    void decreaseStock(Long productId, Integer quantity);
    void increaseStock(Long productId, Integer quantity);
    
    void activateProduct(Long productId);
    void deactivateProduct(Long productId);
    void toggleFeatured(Long productId);
    
    void incrementViewCount(Long productId);
    void incrementSoldCount(Long productId, Integer quantity);
    
    List<Product> getLowStockProducts(Integer threshold);
    List<Product> getOutOfStockProducts();
    List<Product> getTopSellingProducts();
    List<Product> getTopRatedProducts();
    
    long getTotalProductsCount();
    long getActiveProductsCount();
    long getOutOfStockCount();
}

