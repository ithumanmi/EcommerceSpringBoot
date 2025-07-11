package com.example.ecommerce.service;

import com.example.ecommerce.model.CartItem;
import java.util.List;

public interface CartItemService {
    List<CartItem> getAllCartItems();
    CartItem getCartItemById(Long id);
    CartItem createCartItem(CartItem cartItem);
} 
