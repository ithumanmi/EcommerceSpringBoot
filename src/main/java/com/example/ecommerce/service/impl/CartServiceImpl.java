package com.example.ecommerce.service.impl;

import com.example.ecommerce.dto.AddToCartDTO;
import com.example.ecommerce.dto.CartDTO;
import com.example.ecommerce.dto.CartItemDTO;
import com.example.ecommerce.dto.UpdateCartItemDTO;
import com.example.ecommerce.exception.CartNotFoundException;
import com.example.ecommerce.exception.InsufficientStockException;
import com.example.ecommerce.exception.ProductNotFoundException;
import com.example.ecommerce.model.Cart;
import com.example.ecommerce.model.CartItem;
import com.example.ecommerce.model.Product;
import com.example.ecommerce.repository.CartItemRepository;
import com.example.ecommerce.repository.CartRepository;
import com.example.ecommerce.repository.ProductRepository;
import com.example.ecommerce.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class CartServiceImpl implements CartService {
    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Cart> getAllCarts() {
        return cartRepository.findAll();
    }

    @Override
    public Cart getCartById(Long id) {
        return cartRepository.findById(id)
                .orElseThrow(() -> new CartNotFoundException(id));
    }

    @Override
    public Cart getCartByUserId(Long userId) {
        return cartRepository.findByUserId(userId)
                .orElseThrow(() -> new CartNotFoundException("Cart not found for user ID: " + userId));
    }

    @Override
    public Cart getOrCreateCart(Long userId) {
        return cartRepository.findByUserId(userId)
                .orElseGet(() -> {
                    Cart newCart = new Cart();
                    newCart.setUserId(userId);
                    return cartRepository.save(newCart);
                });
    }

    @Override
    public CartDTO getCartDTOByUserId(Long userId) {
        Cart cart = getOrCreateCart(userId);
        return convertToDTO(cart);
    }

    @Override
    public CartItem addToCart(Long userId, AddToCartDTO addToCartDTO) {
        Cart cart = getOrCreateCart(userId);
        
        Product product = productRepository.findById(addToCartDTO.getProductId())
                .orElseThrow(() -> new ProductNotFoundException(addToCartDTO.getProductId()));

        if (!product.getIsActive()) {
            throw new RuntimeException("Product is not available");
        }

        if (product.getStock() < addToCartDTO.getQuantity()) {
            throw new InsufficientStockException(product.getName(), addToCartDTO.getQuantity(), product.getStock());
        }

        Optional<CartItem> existingItem = cartItemRepository
                .findByCartIdAndProductId(cart.getId(), product.getId());

        CartItem cartItem;
        if (existingItem.isPresent()) {
            cartItem = existingItem.get();
            int newQuantity = cartItem.getQuantity() + addToCartDTO.getQuantity();
            
            if (product.getStock() < newQuantity) {
                throw new InsufficientStockException(product.getName(), newQuantity, product.getStock());
            }
            
            cartItem.setQuantity(newQuantity);
        } else {
            cartItem = new CartItem();
            cartItem.setCartId(cart.getId());
            cartItem.setProductId(product.getId());
            cartItem.setQuantity(addToCartDTO.getQuantity());
        }

        cartItem.setProductName(product.getName());
        cartItem.setProductSku(product.getSku());
        cartItem.setProductImage(product.getImageUrl());
        cartItem.setPrice(product.getPrice());
        cartItem.setDiscountPrice(product.getDiscountPrice());

        BigDecimal itemPrice = product.getDiscountPrice() != null ? product.getDiscountPrice() : product.getPrice();
        BigDecimal subtotal = itemPrice.multiply(BigDecimal.valueOf(cartItem.getQuantity()));
        cartItem.setSubtotal(subtotal);

        return cartItemRepository.save(cartItem);
    }

    @Override
    public CartItem updateCartItem(Long userId, Long cartItemId, UpdateCartItemDTO updateCartItemDTO) {
        Cart cart = getCartByUserId(userId);
        
        CartItem cartItem = cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new RuntimeException("Cart item not found"));

        if (!cartItem.getCartId().equals(cart.getId())) {
            throw new RuntimeException("Cart item does not belong to user's cart");
        }

        Product product = productRepository.findById(cartItem.getProductId())
                .orElseThrow(() -> new ProductNotFoundException(cartItem.getProductId()));

        if (product.getStock() < updateCartItemDTO.getQuantity()) {
            throw new InsufficientStockException(product.getName(), updateCartItemDTO.getQuantity(), product.getStock());
        }

        cartItem.setQuantity(updateCartItemDTO.getQuantity());
        
        BigDecimal itemPrice = cartItem.getDiscountPrice() != null ? cartItem.getDiscountPrice() : cartItem.getPrice();
        BigDecimal subtotal = itemPrice.multiply(BigDecimal.valueOf(cartItem.getQuantity()));
        cartItem.setSubtotal(subtotal);

        return cartItemRepository.save(cartItem);
    }

    @Override
    public void removeFromCart(Long userId, Long cartItemId) {
        Cart cart = getCartByUserId(userId);
        
        CartItem cartItem = cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new RuntimeException("Cart item not found"));

        if (!cartItem.getCartId().equals(cart.getId())) {
            throw new RuntimeException("Cart item does not belong to user's cart");
        }

        cartItemRepository.delete(cartItem);
    }

    @Override
    public void clearCart(Long userId) {
        Cart cart = getCartByUserId(userId);
        cartItemRepository.deleteByCartId(cart.getId());
    }

    @Override
    public void syncCartPrices(Long userId) {
        Cart cart = getCartByUserId(userId);
        List<CartItem> items = cartItemRepository.findByCartId(cart.getId());

        for (CartItem item : items) {
            Product product = productRepository.findById(item.getProductId()).orElse(null);
            if (product != null) {
                item.setPrice(product.getPrice());
                item.setDiscountPrice(product.getDiscountPrice());
                item.setProductName(product.getName());
                item.setProductSku(product.getSku());
                item.setProductImage(product.getImageUrl());

                BigDecimal itemPrice = product.getDiscountPrice() != null ? product.getDiscountPrice() : product.getPrice();
                BigDecimal subtotal = itemPrice.multiply(BigDecimal.valueOf(item.getQuantity()));
                item.setSubtotal(subtotal);

                cartItemRepository.save(item);
            }
        }
    }

    @Override
    public int getCartItemCount(Long userId) {
        Optional<Cart> cartOpt = cartRepository.findByUserId(userId);
        if (cartOpt.isEmpty()) {
            return 0;
        }
        return (int) cartItemRepository.countByCartId(cartOpt.get().getId());
    }

    private CartDTO convertToDTO(Cart cart) {
        CartDTO dto = new CartDTO();
        dto.setId(cart.getId());
        dto.setUserId(cart.getUserId());
        dto.setCreatedAt(cart.getCreatedAt());

        List<CartItem> items = cartItemRepository.findByCartId(cart.getId());
        List<CartItemDTO> itemDTOs = items.stream()
                .map(this::convertItemToDTO)
                .collect(Collectors.toList());

        dto.setCartItems(itemDTOs);
        dto.setTotalItems(items.size());

        BigDecimal totalAmount = items.stream()
                .map(CartItem::getSubtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        dto.setTotalAmount(totalAmount);

        return dto;
    }

    private CartItemDTO convertItemToDTO(CartItem item) {
        CartItemDTO dto = new CartItemDTO();
        dto.setId(item.getId());
        dto.setCartId(item.getCartId());
        dto.setProductId(item.getProductId());
        dto.setProductName(item.getProductName());
        dto.setProductSku(item.getProductSku());
        dto.setProductImage(item.getProductImage());
        dto.setPrice(item.getPrice());
        dto.setDiscountPrice(item.getDiscountPrice());
        dto.setQuantity(item.getQuantity());
        dto.setSubtotal(item.getSubtotal());
        return dto;
    }
} 
