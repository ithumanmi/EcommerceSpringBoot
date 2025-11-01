package com.example.ecommerce.exception;

public class CustomerAddressAccessDeniedException extends RuntimeException {
    public CustomerAddressAccessDeniedException(String message) {
        super(message);
    }
}

