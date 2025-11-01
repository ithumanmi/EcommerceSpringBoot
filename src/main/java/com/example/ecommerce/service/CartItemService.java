package com.example.ecommerce.service;

import com.example.ecommerce.model.CartItem;
import org.springframework.lang.NonNull;

import java.util.List;

public interface CartItemService {
    List<CartItem> getAllCartItems();
    CartItem getCartItemById(@NonNull Long id);
    @NonNull CartItem createCartItem(CartItem cartItem);
} 
