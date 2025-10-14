package com.example.ecommerce.dto;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class CouponDTO {
    private Long id;
    
    @NotBlank(message = "Coupon code is required")
    @Size(min = 3, max = 50, message = "Coupon code must be between 3 and 50 characters")
    private String code;
    
    @Size(max = 200, message = "Description cannot exceed 200 characters")
    private String description;
    
    @NotBlank(message = "Discount type is required")
    private String discountType;
    
    @NotNull(message = "Discount value is required")
    @DecimalMin(value = "0.0", message = "Discount value must be positive")
    private BigDecimal discountValue;
    
    @DecimalMin(value = "0.0", message = "Minimum purchase amount must be positive")
    private BigDecimal minPurchaseAmount;
    
    @DecimalMin(value = "0.0", message = "Maximum discount amount must be positive")
    private BigDecimal maxDiscountAmount;
    
    private LocalDateTime startDate;
    private LocalDateTime expiryDate;
    
    @Min(value = 0, message = "Usage limit must be positive")
    private Integer usageLimit;
    
    private Integer usageCount;
    
    @Min(value = 0, message = "Usage per user must be positive")
    private Integer usagePerUser;
    
    private Boolean isActive;
    private String applicableTo;
    private String applicableCategoryIds;
    private String applicableProductIds;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public CouponDTO() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }
    
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    
    public String getDiscountType() { return discountType; }
    public void setDiscountType(String discountType) { this.discountType = discountType; }
    
    public BigDecimal getDiscountValue() { return discountValue; }
    public void setDiscountValue(BigDecimal discountValue) { this.discountValue = discountValue; }
    
    public BigDecimal getMinPurchaseAmount() { return minPurchaseAmount; }
    public void setMinPurchaseAmount(BigDecimal minPurchaseAmount) { this.minPurchaseAmount = minPurchaseAmount; }
    
    public BigDecimal getMaxDiscountAmount() { return maxDiscountAmount; }
    public void setMaxDiscountAmount(BigDecimal maxDiscountAmount) { this.maxDiscountAmount = maxDiscountAmount; }
    
    public LocalDateTime getStartDate() { return startDate; }
    public void setStartDate(LocalDateTime startDate) { this.startDate = startDate; }
    
    public LocalDateTime getExpiryDate() { return expiryDate; }
    public void setExpiryDate(LocalDateTime expiryDate) { this.expiryDate = expiryDate; }
    
    public Integer getUsageLimit() { return usageLimit; }
    public void setUsageLimit(Integer usageLimit) { this.usageLimit = usageLimit; }
    
    public Integer getUsageCount() { return usageCount; }
    public void setUsageCount(Integer usageCount) { this.usageCount = usageCount; }
    
    public Integer getUsagePerUser() { return usagePerUser; }
    public void setUsagePerUser(Integer usagePerUser) { this.usagePerUser = usagePerUser; }
    
    public Boolean getIsActive() { return isActive; }
    public void setIsActive(Boolean isActive) { this.isActive = isActive; }
    
    public String getApplicableTo() { return applicableTo; }
    public void setApplicableTo(String applicableTo) { this.applicableTo = applicableTo; }
    
    public String getApplicableCategoryIds() { return applicableCategoryIds; }
    public void setApplicableCategoryIds(String applicableCategoryIds) { this.applicableCategoryIds = applicableCategoryIds; }
    
    public String getApplicableProductIds() { return applicableProductIds; }
    public void setApplicableProductIds(String applicableProductIds) { this.applicableProductIds = applicableProductIds; }
    
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}

