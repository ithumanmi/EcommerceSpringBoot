package com.example.ecommerce.exception;

public class SystemSettingNotFoundException extends RuntimeException {
    public SystemSettingNotFoundException(String message) {
        super(message);
    }
    
    public SystemSettingNotFoundException(Long id) {
        super("System setting not found with ID: " + id);
    }
}

