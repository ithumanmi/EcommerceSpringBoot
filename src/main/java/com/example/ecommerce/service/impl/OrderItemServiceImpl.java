package com.example.ecommerce.service.impl;

import com.example.ecommerce.model.OrderItem;
import com.example.ecommerce.repository.OrderItemRepository;
import com.example.ecommerce.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderItemServiceImpl implements OrderItemService {
    @Autowired
    private OrderItemRepository orderItemRepository;

    @Override
    public List<OrderItem> getAllOrderItems() {
        return orderItemRepository.findAll();
    }

    @Override
    public OrderItem getOrderItemById(Long id) {
        return orderItemRepository.findById(id).orElse(null);
    }

    @Override
    public OrderItem createOrderItem(OrderItem orderItem) {
        return orderItemRepository.save(orderItem);
    }
} 
