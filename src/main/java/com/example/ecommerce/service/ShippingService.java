package com.example.ecommerce.service;

import com.example.ecommerce.model.Shipping;
import java.util.List;

public interface ShippingService {
    List<Shipping> getAllShippings();
    Shipping getShippingById(Long id);
    Shipping createShipping(Shipping shipping);
} 
