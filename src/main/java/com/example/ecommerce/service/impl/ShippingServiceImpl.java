package com.example.ecommerce.service.impl;

import com.example.ecommerce.model.Shipping;
import com.example.ecommerce.repository.ShippingRepository;
import com.example.ecommerce.service.ShippingService;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShippingServiceImpl implements ShippingService {
    private final ShippingRepository shippingRepository;

    public ShippingServiceImpl(ShippingRepository shippingRepository) {
        this.shippingRepository = shippingRepository;
    }

    @Override
    public List<Shipping> getAllShippings() {
        return shippingRepository.findAll();
    }

    @Override
    public Shipping getShippingById(@NonNull Long id) {
        return shippingRepository.findById(id).orElse(null);
    }

    @Override
    @SuppressWarnings("null")
    public @NonNull Shipping createShipping(Shipping shipping) {
        return shippingRepository.save(shipping);
    }
} 
