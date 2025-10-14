package com.example.ecommerce.service.impl;

import com.example.ecommerce.dto.DashboardStatsDTO;
import com.example.ecommerce.model.Order;
import com.example.ecommerce.repository.*;
import com.example.ecommerce.service.AdminDashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class AdminDashboardServiceImpl implements AdminDashboardService {
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private ProductRepository productRepository;
    
    @Autowired
    private OrderRepository orderRepository;
    
    @Autowired
    private CouponRepository couponRepository;
    
    @Autowired
    private PromotionRepository promotionRepository;

    @Override
    public DashboardStatsDTO getDashboardStats() {
        DashboardStatsDTO stats = new DashboardStatsDTO();
        
        stats.setTotalUsers(userRepository.count());
        stats.setActiveUsers(userRepository.countByIsActive(true));
        
        stats.setTotalProducts(productRepository.count());
        stats.setActiveProducts(productRepository.countByIsActive(true));
        stats.setOutOfStockProducts(productRepository.countOutOfStockProducts());
        
        stats.setTotalOrders(orderRepository.count());
        stats.setPendingOrders(orderRepository.countByStatus("PENDING"));
        stats.setProcessingOrders(orderRepository.countByStatus("PROCESSING"));
        stats.setCompletedOrders(orderRepository.countByStatus("COMPLETED"));
        stats.setCancelledOrders(orderRepository.countByStatus("CANCELLED"));
        
        List<Order> allOrders = orderRepository.findAll();
        BigDecimal totalRevenue = allOrders.stream()
                .filter(o -> "COMPLETED".equals(o.getStatus()))
                .map(Order::getTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        stats.setTotalRevenue(totalRevenue);
        
        LocalDateTime today = LocalDateTime.now().withHour(0).withMinute(0).withSecond(0);
        LocalDateTime tomorrow = today.plusDays(1);
        List<Order> todayOrders = orderRepository.findOrdersByDateRange(today, tomorrow);
        stats.setOrdersToday((long) todayOrders.size());
        BigDecimal revenueToday = todayOrders.stream()
                .filter(o -> "COMPLETED".equals(o.getStatus()))
                .map(Order::getTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        stats.setRevenueToday(revenueToday);
        
        LocalDateTime startOfMonth = LocalDateTime.now().withDayOfMonth(1).withHour(0).withMinute(0).withSecond(0);
        List<Order> monthOrders = orderRepository.findOrdersByDateRange(startOfMonth, LocalDateTime.now());
        stats.setOrdersThisMonth((long) monthOrders.size());
        BigDecimal revenueThisMonth = monthOrders.stream()
                .filter(o -> "COMPLETED".equals(o.getStatus()))
                .map(Order::getTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        stats.setRevenueThisMonth(revenueThisMonth);
        
        stats.setNewCustomersToday(userRepository.countByIsActive(true));
        stats.setNewCustomersThisMonth(userRepository.count());
        
        stats.setActiveCoupons((long) couponRepository.findByIsActive(true).size());
        stats.setActivePromotions((long) promotionRepository.findByIsActive(true).size());
        
        return stats;
    }
}

