package com.example.ecommerce.service;

import com.example.ecommerce.dto.CreateOrderDTO;
import com.example.ecommerce.dto.OrderDTO;
import com.example.ecommerce.model.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderService {
    List<Order> getAllOrders();
    Order getOrderById(Long id);
    OrderDTO getOrderDTOById(Long id);
    Order createOrder(CreateOrderDTO createOrderDTO, Long userId);
    void cancelOrder(Long id);
    void updateOrderStatus(Long id, String status);
    void updatePaymentStatus(Long id, String paymentStatus);
    
    List<Order> getOrdersByUser(Long userId);
    List<Order> getOrdersByStatus(String status);
    List<Order> getOrdersByPaymentStatus(String paymentStatus);
    List<Order> getOrdersByDateRange(LocalDateTime startDate, LocalDateTime endDate);
    List<Order> getUserOrdersByStatus(Long userId, String status);
    List<Order> getRecentOrders();
    
    Page<Order> getAllOrdersPaginated(Pageable pageable);
    Page<Order> getUserOrdersPaginated(Long userId, Pageable pageable);
    
    Order findByOrderNumber(String orderNumber);
    
    long getTotalOrdersCount();
    long getOrderCountByStatus(String status);
    long getOrdersCountFromDate(LocalDateTime startDate);
} 
