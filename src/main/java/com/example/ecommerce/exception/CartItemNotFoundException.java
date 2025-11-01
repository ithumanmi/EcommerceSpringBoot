package com.example.ecommerce.exception;

public class CartItemNotFoundException extends RuntimeException {
    public CartItemNotFoundException(String message) {
        super(message);
    }
    
    public CartItemNotFoundException(Long cartItemId) {
        super("Cart item not found with ID: " + cartItemId);
    }
}

