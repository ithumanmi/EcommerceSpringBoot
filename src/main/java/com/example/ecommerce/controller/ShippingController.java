package com.example.ecommerce.controller;

import com.example.ecommerce.model.Shipping;
import com.example.ecommerce.service.ShippingService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/shippings")
public class ShippingController {
    private final ShippingService shippingService;

    public ShippingController(ShippingService shippingService) {
        this.shippingService = shippingService;
    }

    @GetMapping
    public List<Shipping> getAllShippings() {
        return shippingService.getAllShippings();
    }

    @GetMapping("/{id}")
    public Shipping getShippingById(@PathVariable Long id) {
        return shippingService.getShippingById(id);
    }

    @PostMapping
    public Shipping createShipping(@RequestBody Shipping shipping) {
        return shippingService.createShipping(shipping);
    }
} 
