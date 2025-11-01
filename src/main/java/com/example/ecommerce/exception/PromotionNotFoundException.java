package com.example.ecommerce.exception;

public class PromotionNotFoundException extends RuntimeException {
    public PromotionNotFoundException(String message) {
        super(message);
    }
    
    public PromotionNotFoundException(Long id) {
        super("Promotion not found with ID: " + id);
    }
}

