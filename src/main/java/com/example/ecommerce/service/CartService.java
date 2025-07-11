package com.example.ecommerce.service;

import com.example.ecommerce.model.Cart;
import java.util.List;

public interface CartService {
    List<Cart> getAllCarts();
    Cart getCartById(Long id);
    Cart createCart(Cart cart);
} 
