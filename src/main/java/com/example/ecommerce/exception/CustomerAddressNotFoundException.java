package com.example.ecommerce.exception;

public class CustomerAddressNotFoundException extends RuntimeException {
    public CustomerAddressNotFoundException(String message) {
        super(message);
    }
    
    public CustomerAddressNotFoundException(Long addressId) {
        super("Customer address not found with ID: " + addressId);
    }
}

