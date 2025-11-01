package com.example.ecommerce.service;

import com.example.ecommerce.dto.CreateOrderDTO;
import com.example.ecommerce.dto.OrderDTO;
import com.example.ecommerce.model.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.NonNull;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderService {
    List<Order> getAllOrders();
    @NonNull Order getOrderById(@NonNull Long id);
    @NonNull OrderDTO getOrderDTOById(@NonNull Long id);
    Order createOrder(CreateOrderDTO createOrderDTO, Long userId);
    void cancelOrder(@NonNull Long id);
    void updateOrderStatus(@NonNull Long id, String status);
    void updatePaymentStatus(@NonNull Long id, String paymentStatus);
    
    List<Order> getOrdersByUser(Long userId);
    List<Order> getOrdersByStatus(String status);
    List<Order> getOrdersByPaymentStatus(String paymentStatus);
    List<Order> getOrdersByDateRange(LocalDateTime startDate, LocalDateTime endDate);
    List<Order> getUserOrdersByStatus(Long userId, String status);
    List<Order> getRecentOrders();
    
    Page<Order> getAllOrdersPaginated(@NonNull Pageable pageable);
    Page<Order> getUserOrdersPaginated(Long userId, Pageable pageable);
    
    Order findByOrderNumber(String orderNumber);
    
    long getTotalOrdersCount();
    long getOrderCountByStatus(String status);
    long getOrdersCountFromDate(LocalDateTime startDate);
} 
