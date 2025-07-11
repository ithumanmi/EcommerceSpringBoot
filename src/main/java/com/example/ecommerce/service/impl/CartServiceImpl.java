package com.example.ecommerce.service.impl;

import com.example.ecommerce.model.Cart;
import com.example.ecommerce.repository.CartRepository;
import com.example.ecommerce.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private CartRepository cartRepository;

    @Override
    public List<Cart> getAllCarts() {
        return cartRepository.findAll();
    }

    @Override
    public Cart getCartById(Long id) {
        return cartRepository.findById(id).orElse(null);
    }

    @Override
    public Cart createCart(Cart cart) {
        return cartRepository.save(cart);
    }
} 
