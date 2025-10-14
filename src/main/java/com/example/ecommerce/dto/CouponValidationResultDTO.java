package com.example.ecommerce.dto;

import java.math.BigDecimal;

public class CouponValidationResultDTO {
    private Boolean isValid;
    private String message;
    private BigDecimal discountAmount;
    private BigDecimal finalAmount;
    private String couponCode;

    public CouponValidationResultDTO() {}

    public CouponValidationResultDTO(Boolean isValid, String message, BigDecimal discountAmount, BigDecimal finalAmount, String couponCode) {
        this.isValid = isValid;
        this.message = message;
        this.discountAmount = discountAmount;
        this.finalAmount = finalAmount;
        this.couponCode = couponCode;
    }

    public Boolean getIsValid() { return isValid; }
    public void setIsValid(Boolean isValid) { this.isValid = isValid; }
    
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
    
    public BigDecimal getDiscountAmount() { return discountAmount; }
    public void setDiscountAmount(BigDecimal discountAmount) { this.discountAmount = discountAmount; }
    
    public BigDecimal getFinalAmount() { return finalAmount; }
    public void setFinalAmount(BigDecimal finalAmount) { this.finalAmount = finalAmount; }
    
    public String getCouponCode() { return couponCode; }
    public void setCouponCode(String couponCode) { this.couponCode = couponCode; }
}

