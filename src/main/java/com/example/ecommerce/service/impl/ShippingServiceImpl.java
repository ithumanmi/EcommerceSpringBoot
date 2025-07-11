package com.example.ecommerce.service.impl;

import com.example.ecommerce.model.Shipping;
import com.example.ecommerce.repository.ShippingRepository;
import com.example.ecommerce.service.ShippingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShippingServiceImpl implements ShippingService {
    @Autowired
    private ShippingRepository shippingRepository;

    @Override
    public List<Shipping> getAllShippings() {
        return shippingRepository.findAll();
    }

    @Override
    public Shipping getShippingById(Long id) {
        return shippingRepository.findById(id).orElse(null);
    }

    @Override
    public Shipping createShipping(Shipping shipping) {
        return shippingRepository.save(shipping);
    }
} 
