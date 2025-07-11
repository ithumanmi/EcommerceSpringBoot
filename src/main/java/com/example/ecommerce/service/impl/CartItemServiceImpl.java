package com.example.ecommerce.service.impl;

import com.example.ecommerce.model.CartItem;
import com.example.ecommerce.repository.CartItemRepository;
import com.example.ecommerce.service.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartItemServiceImpl implements CartItemService {
    @Autowired
    private CartItemRepository cartItemRepository;

    @Override
    public List<CartItem> getAllCartItems() {
        return cartItemRepository.findAll();
    }

    @Override
    public CartItem getCartItemById(Long id) {
        return cartItemRepository.findById(id).orElse(null);
    }

    @Override
    public CartItem createCartItem(CartItem cartItem) {
        return cartItemRepository.save(cartItem);
    }
} 
