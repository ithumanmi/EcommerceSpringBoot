package com.example.ecommerce.dto;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;

public class ValidateCouponDTO {
    @NotBlank(message = "Coupon code is required")
    private String code;
    
    @NotNull(message = "Cart total is required")
    @DecimalMin(value = "0.0", message = "Cart total must be positive")
    private BigDecimal cartTotal;

    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }
    
    public BigDecimal getCartTotal() { return cartTotal; }
    public void setCartTotal(BigDecimal cartTotal) { this.cartTotal = cartTotal; }
}

