package com.example.ecommerce.service;

import com.example.ecommerce.model.OrderItem;
import java.util.List;

public interface OrderItemService {
    List<OrderItem> getAllOrderItems();
    OrderItem getOrderItemById(Long id);
    OrderItem createOrderItem(OrderItem orderItem);
} 
