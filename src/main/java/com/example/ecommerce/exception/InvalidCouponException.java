package com.example.ecommerce.exception;

public class InvalidCouponException extends RuntimeException {
    public InvalidCouponException(String message) {
        super(message);
    }
}

