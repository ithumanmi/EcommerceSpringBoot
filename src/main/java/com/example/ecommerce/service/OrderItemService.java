package com.example.ecommerce.service;

import com.example.ecommerce.model.OrderItem;
import org.springframework.lang.NonNull;

import java.util.List;

public interface OrderItemService {
    List<OrderItem> getAllOrderItems();
    OrderItem getOrderItemById(@NonNull Long id);
    @NonNull OrderItem createOrderItem(OrderItem orderItem);
} 
