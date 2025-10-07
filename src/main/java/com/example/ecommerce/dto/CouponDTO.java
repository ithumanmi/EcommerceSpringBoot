package com.example.ecommerce.dto;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Data Transfer Object for Coupon
 */
public class CouponDTO {
    private Long id;
    
    @NotBlank(message = "Coupon code is required")
    @Size(min = 3, max = 50, message = "Coupon code must be between 3 and 50 characters")
    private String code;
    
    @DecimalMin(value = "0.0", message = "Discount cannot be negative")
    private BigDecimal discount;
    
    private LocalDate expiryDate;
    
    @Min(value = 1, message = "Usage limit must be at least 1")
    private Integer usageLimit;
    
    private Integer usedCount;
    private Boolean isActive;

    // Constructors
    public CouponDTO() {}

    public CouponDTO(Long id, String code, BigDecimal discount, LocalDate expiryDate, Integer usageLimit) {
        this.id = id;
        this.code = code;
        this.discount = discount;
        this.expiryDate = expiryDate;
        this.usageLimit = usageLimit;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    public Integer getUsageLimit() {
        return usageLimit;
    }

    public void setUsageLimit(Integer usageLimit) {
        this.usageLimit = usageLimit;
    }

    public Integer getUsedCount() {
        return usedCount;
    }

    public void setUsedCount(Integer usedCount) {
        this.usedCount = usedCount;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }
}
