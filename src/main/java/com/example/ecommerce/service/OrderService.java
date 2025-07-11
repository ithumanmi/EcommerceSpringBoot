package com.example.ecommerce.service;

import com.example.ecommerce.model.Order;
import java.util.List;

public interface OrderService {
    List<Order> getAllOrders();
    Order getOrderById(Long id);
    Order createOrder(Order order);
} 
