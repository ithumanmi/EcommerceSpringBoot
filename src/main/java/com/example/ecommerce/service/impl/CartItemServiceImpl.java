package com.example.ecommerce.service.impl;

import com.example.ecommerce.model.CartItem;
import com.example.ecommerce.repository.CartItemRepository;
import com.example.ecommerce.service.CartItemService;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartItemServiceImpl implements CartItemService {
    private final CartItemRepository cartItemRepository;

    public CartItemServiceImpl(CartItemRepository cartItemRepository) {
        this.cartItemRepository = cartItemRepository;
    }

    @Override
    public List<CartItem> getAllCartItems() {
        return cartItemRepository.findAll();
    }

    @Override
    public CartItem getCartItemById(@NonNull Long id) {
        return cartItemRepository.findById(id).orElse(null);
    }

    @Override
    @SuppressWarnings("null")
    public @NonNull CartItem createCartItem(CartItem cartItem) {
        return cartItemRepository.save(cartItem);
    }
} 
