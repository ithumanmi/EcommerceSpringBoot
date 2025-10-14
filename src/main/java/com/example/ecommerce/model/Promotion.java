package com.example.ecommerce.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "promotions")
public class Promotion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 3, max = 100)
    @Column(nullable = false, length = 100)
    private String name;

    @Size(max = 500)
    @Column(length = 500)
    private String description;

    @NotNull
    @Column(name = "promotion_type", nullable = false, length = 50)
    private String promotionType;

    @NotNull
    @DecimalMin("0.0")
    @Column(name = "discount_value", nullable = false, precision = 10, scale = 2)
    private BigDecimal discountValue;

    @DecimalMin("0.0")
    @Column(name = "min_purchase_amount", precision = 10, scale = 2)
    private BigDecimal minPurchaseAmount;

    @Column(name = "start_date")
    private LocalDateTime startDate;

    @Column(name = "end_date")
    private LocalDateTime endDate;

    @Column(name = "is_active")
    private Boolean isActive = true;

    @Column(name = "priority")
    private Integer priority = 0;

    @Column(name = "applicable_to", length = 50)
    private String applicableTo;

    @Column(name = "banner_image", length = 500)
    private String bannerImage;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
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

