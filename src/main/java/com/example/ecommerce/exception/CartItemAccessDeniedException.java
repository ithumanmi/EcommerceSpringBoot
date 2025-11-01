package com.example.ecommerce.exception;

public class CartItemAccessDeniedException extends RuntimeException {
    public CartItemAccessDeniedException(String message) {
        super(message);
    }
}

