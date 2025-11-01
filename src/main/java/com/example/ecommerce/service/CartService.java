package com.example.ecommerce.service;

import com.example.ecommerce.dto.AddToCartDTO;
import com.example.ecommerce.dto.CartDTO;
import com.example.ecommerce.dto.UpdateCartItemDTO;
import com.example.ecommerce.model.Cart;
import com.example.ecommerce.model.CartItem;
import org.springframework.lang.NonNull;

import java.util.List;

public interface CartService {
    List<Cart> getAllCarts();
    @NonNull Cart getCartById(@NonNull Long id);
    Cart getCartByUserId(Long userId);
    Cart getOrCreateCart(Long userId);
    CartDTO getCartDTOByUserId(Long userId);
    
    CartItem addToCart(Long userId, AddToCartDTO addToCartDTO);
    CartItem updateCartItem(Long userId, @NonNull Long cartItemId, UpdateCartItemDTO updateCartItemDTO);
    void removeFromCart(Long userId, @NonNull Long cartItemId);
    void clearCart(Long userId);
    
    void syncCartPrices(Long userId);
    
    int getCartItemCount(Long userId);
} 
