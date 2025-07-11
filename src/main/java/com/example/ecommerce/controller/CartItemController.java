package com.example.ecommerce.controller;

import com.example.ecommerce.model.CartItem;
import com.example.ecommerce.service.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart-items")
public class CartItemController {
    @Autowired
    private CartItemService cartItemService;

    @GetMapping
    public List<CartItem> getAllCartItems() {
        return cartItemService.getAllCartItems();
    }

    @GetMapping("/{id}")
    public CartItem getCartItemById(@PathVariable Long id) {
        return cartItemService.getCartItemById(id);
    }

    @PostMapping
    public CartItem createCartItem(@RequestBody CartItem cartItem) {
        return cartItemService.createCartItem(cartItem);
    }
} 
