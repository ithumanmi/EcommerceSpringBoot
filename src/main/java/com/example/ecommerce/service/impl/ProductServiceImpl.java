package com.example.ecommerce.service.impl;

import com.example.ecommerce.dto.UpdateProductDTO;
import com.example.ecommerce.exception.InsufficientStockException;
import com.example.ecommerce.exception.ProductNotFoundException;
import com.example.ecommerce.model.Product;
import com.example.ecommerce.repository.ProductRepository;
import com.example.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
    }

    @Override
    public Product createProduct(Product product) {
        if (product.getIsActive() == null) {
            product.setIsActive(true);
        }
        if (product.getIsFeatured() == null) {
            product.setIsFeatured(false);
        }
        if (product.getViewCount() == null) {
            product.setViewCount(0);
        }
        if (product.getSoldCount() == null) {
            product.setSoldCount(0);
        }
        if (product.getReviewCount() == null) {
            product.setReviewCount(0);
        }
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Long id, UpdateProductDTO updateProductDTO) {
        Product product = getProductById(id);
        
        if (updateProductDTO.getName() != null) {
            product.setName(updateProductDTO.getName());
        }
        if (updateProductDTO.getDescription() != null) {
            product.setDescription(updateProductDTO.getDescription());
        }
        if (updateProductDTO.getPrice() != null) {
            product.setPrice(updateProductDTO.getPrice());
        }
        if (updateProductDTO.getDiscountPrice() != null) {
            product.setDiscountPrice(updateProductDTO.getDiscountPrice());
        }
        if (updateProductDTO.getStock() != null) {
            product.setStock(updateProductDTO.getStock());
        }
        if (updateProductDTO.getCategoryId() != null) {
            product.setCategoryId(updateProductDTO.getCategoryId());
        }
        if (updateProductDTO.getSku() != null) {
            product.setSku(updateProductDTO.getSku());
        }
        if (updateProductDTO.getBrand() != null) {
            product.setBrand(updateProductDTO.getBrand());
        }
        if (updateProductDTO.getImageUrl() != null) {
            product.setImageUrl(updateProductDTO.getImageUrl());
        }
        
        return productRepository.save(product);
    }

    @Override
    public void deleteProduct(Long id) {
        Product product = getProductById(id);
        productRepository.delete(product);
    }

    @Override
    public Product findBySku(String sku) {
        return productRepository.findBySku(sku)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with SKU: " + sku));
    }

    @Override
    public List<Product> getProductsByCategory(Long categoryId) {
        return productRepository.findByCategoryId(categoryId);
    }

    @Override
    public List<Product> getProductsByBrand(String brand) {
        return productRepository.findByBrand(brand);
    }

    @Override
    public List<Product> getActiveProducts() {
        return productRepository.findByIsActive(true);
    }

    @Override
    public List<Product> getFeaturedProducts() {
        return productRepository.findByIsFeatured(true);
    }

    @Override
    public List<Product> searchProducts(String keyword) {
        return productRepository.searchProducts(keyword);
    }

    @Override
    public List<Product> getProductsByPriceRange(BigDecimal minPrice, BigDecimal maxPrice) {
        return productRepository.findByPriceBetween(minPrice, maxPrice);
    }

    @Override
    public Page<Product> getAllProductsPaginated(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Override
    public void updateStock(Long productId, Integer quantity) {
        Product product = getProductById(productId);
        product.setStock(quantity);
        productRepository.save(product);
    }

    @Override
    public void decreaseStock(Long productId, Integer quantity) {
        Product product = getProductById(productId);
        if (product.getStock() < quantity) {
            throw new InsufficientStockException(product.getName(), quantity, product.getStock());
        }
        product.setStock(product.getStock() - quantity);
        productRepository.save(product);
    }

    @Override
    public void increaseStock(Long productId, Integer quantity) {
        Product product = getProductById(productId);
        product.setStock(product.getStock() + quantity);
        productRepository.save(product);
    }

    @Override
    public void activateProduct(Long productId) {
        Product product = getProductById(productId);
        product.setIsActive(true);
        productRepository.save(product);
    }

    @Override
    public void deactivateProduct(Long productId) {
        Product product = getProductById(productId);
        product.setIsActive(false);
        productRepository.save(product);
    }

    @Override
    public void toggleFeatured(Long productId) {
        Product product = getProductById(productId);
        product.setIsFeatured(!product.getIsFeatured());
        productRepository.save(product);
    }

    @Override
    public void incrementViewCount(Long productId) {
        Product product = getProductById(productId);
        product.setViewCount(product.getViewCount() + 1);
        productRepository.save(product);
    }

    @Override
    public void incrementSoldCount(Long productId, Integer quantity) {
        Product product = getProductById(productId);
        product.setSoldCount(product.getSoldCount() + quantity);
        productRepository.save(product);
    }

    @Override
    public List<Product> getLowStockProducts(Integer threshold) {
        return productRepository.findLowStockProducts(threshold);
    }

    @Override
    public List<Product> getOutOfStockProducts() {
        return productRepository.findOutOfStockProducts();
    }

    @Override
    public List<Product> getTopSellingProducts() {
        return productRepository.findTopSellingProducts();
    }

    @Override
    public List<Product> getTopRatedProducts() {
        return productRepository.findTopRatedProducts();
    }

    @Override
    public long getTotalProductsCount() {
        return productRepository.count();
    }

    @Override
    public long getActiveProductsCount() {
        return productRepository.countByIsActive(true);
    }

    @Override
    public long getOutOfStockCount() {
        return productRepository.countOutOfStockProducts();
    }
}

