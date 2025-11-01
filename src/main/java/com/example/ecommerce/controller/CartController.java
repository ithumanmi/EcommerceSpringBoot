package com.example.ecommerce.controller;

import com.example.ecommerce.dto.AddToCartDTO;
import com.example.ecommerce.dto.ApiResponse;
import com.example.ecommerce.dto.CartDTO;
import com.example.ecommerce.dto.UpdateCartItemDTO;
import com.example.ecommerce.model.Cart;
import com.example.ecommerce.model.CartItem;
import com.example.ecommerce.model.User;
import com.example.ecommerce.service.CartService;
import com.example.ecommerce.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/cart")
@CrossOrigin(origins = "*")
public class CartController {
    private final CartService cartService;
    private final UserService userService;

    public CartController(CartService cartService, UserService userService) {
        this.cartService = cartService;
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<CartDTO> getMyCart(Authentication authentication) {
        String username = authentication.getName();
        User user = userService.findByUsername(username);
        CartDTO cart = cartService.getCartDTOByUserId(user.getId());
        return ResponseEntity.ok(cart);
    }

    @PostMapping("/add")
    public ResponseEntity<CartItem> addToCart(
            @Valid @RequestBody AddToCartDTO addToCartDTO,
            Authentication authentication
    ) {
        String username = authentication.getName();
        User user = userService.findByUsername(username);
        CartItem cartItem = cartService.addToCart(user.getId(), addToCartDTO);
        return ResponseEntity.ok(cartItem);
    }

    @PutMapping("/items/{cartItemId}")
    public ResponseEntity<CartItem> updateCartItem(
            @PathVariable Long cartItemId,
            @Valid @RequestBody UpdateCartItemDTO updateCartItemDTO,
            Authentication authentication
    ) {
        String username = authentication.getName();
        User user = userService.findByUsername(username);
        CartItem cartItem = cartService.updateCartItem(user.getId(), cartItemId, updateCartItemDTO);
        return ResponseEntity.ok(cartItem);
    }

    @DeleteMapping("/items/{cartItemId}")
    public ResponseEntity<ApiResponse<Void>> removeFromCart(
            @PathVariable Long cartItemId,
            Authentication authentication
    ) {
        String username = authentication.getName();
        User user = userService.findByUsername(username);
        cartService.removeFromCart(user.getId(), cartItemId);
        return ResponseEntity.ok(new ApiResponse<>(true, "Item removed from cart successfully"));
    }

    @DeleteMapping("/clear")
    public ResponseEntity<ApiResponse<Void>> clearCart(Authentication authentication) {
        String username = authentication.getName();
        User user = userService.findByUsername(username);
        cartService.clearCart(user.getId());
        return ResponseEntity.ok(new ApiResponse<>(true, "Cart cleared successfully"));
    }

    @GetMapping("/count")
    public ResponseEntity<Map<String, Integer>> getCartItemCount(Authentication authentication) {
        String username = authentication.getName();
        User user = userService.findByUsername(username);
        int count = cartService.getCartItemCount(user.getId());
        Map<String, Integer> response = new HashMap<>();
        response.put("count", count);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/sync")
    public ResponseEntity<ApiResponse<Void>> syncCartPrices(Authentication authentication) {
        String username = authentication.getName();
        User user = userService.findByUsername(username);
        cartService.syncCartPrices(user.getId());
        return ResponseEntity.ok(new ApiResponse<Void>(true, "Cart prices synced successfully"));
    }

    @GetMapping("/all")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Cart>> getAllCarts() {
        List<Cart> carts = cartService.getAllCarts();
        return ResponseEntity.ok(carts);
    }
} 
