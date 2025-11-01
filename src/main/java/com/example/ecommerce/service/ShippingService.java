package com.example.ecommerce.service;

import com.example.ecommerce.model.Shipping;
import org.springframework.lang.NonNull;

import java.util.List;

public interface ShippingService {
    List<Shipping> getAllShippings();
    Shipping getShippingById(@NonNull Long id);
    Shipping createShipping(Shipping shipping);
} 
