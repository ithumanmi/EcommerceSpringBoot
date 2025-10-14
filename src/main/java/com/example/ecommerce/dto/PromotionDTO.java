package com.example.ecommerce.dto;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class PromotionDTO {
    private Long id;
    
    @NotBlank(message = "Promotion name is required")
    @Size(min = 3, max = 100, message = "Name must be between 3 and 100 characters")
    private String name;
    
    @Size(max = 500, message = "Description cannot exceed 500 characters")
    private String description;
    
    @NotBlank(message = "Promotion type is required")
    private String promotionType;
    
    @NotNull(message = "Discount value is required")
    @DecimalMin(value = "0.0", message = "Discount value must be positive")
    private BigDecimal discountValue;
    
    @DecimalMin(value = "0.0", message = "Minimum purchase amount must be positive")
    private BigDecimal minPurchaseAmount;
    
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Boolean isActive;
    private Integer priority;
    private String applicableTo;
    private String bannerImage;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    
    public String getPromotionType() { return promotionType; }
    public void setPromotionType(String promotionType) { this.promotionType = promotionType; }
    
    public BigDecimal getDiscountValue() { return discountValue; }
    public void setDiscountValue(BigDecimal discountValue) { this.discountValue = discountValue; }
    
    public BigDecimal getMinPurchaseAmount() { return minPurchaseAmount; }
    public void setMinPurchaseAmount(BigDecimal minPurchaseAmount) { this.minPurchaseAmount = minPurchaseAmount; }
    
    public LocalDateTime getStartDate() { return startDate; }
    public void setStartDate(LocalDateTime startDate) { this.startDate = startDate; }
    
    public LocalDateTime getEndDate() { return endDate; }
    public void setEndDate(LocalDateTime endDate) { this.endDate = endDate; }
    
    public Boolean getIsActive() { return isActive; }
    public void setIsActive(Boolean isActive) { this.isActive = isActive; }
    
    public Integer getPriority() { return priority; }
    public void setPriority(Integer priority) { this.priority = priority; }
    
    public String getApplicableTo() { return applicableTo; }
    public void setApplicableTo(String applicableTo) { this.applicableTo = applicableTo; }
    
    public String getBannerImage() { return bannerImage; }
    public void setBannerImage(String bannerImage) { this.bannerImage = bannerImage; }
    
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}

