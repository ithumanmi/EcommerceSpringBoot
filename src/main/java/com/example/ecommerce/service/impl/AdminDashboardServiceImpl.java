package com.example.ecommerce.service.impl;

import com.example.ecommerce.dto.DashboardStatsDTO;
import com.example.ecommerce.model.Order;
import com.example.ecommerce.repository.*;
import com.example.ecommerce.service.AdminDashboardService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class AdminDashboardServiceImpl implements AdminDashboardService {
    private static final String ORDER_STATUS_PENDING = "PENDING";
    private static final String ORDER_STATUS_PROCESSING = "PROCESSING";
    private static final String ORDER_STATUS_COMPLETED = "COMPLETED";
    private static final String ORDER_STATUS_CANCELLED = "CANCELLED";

    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;
    private final CouponRepository couponRepository;
    private final PromotionRepository promotionRepository;

    public AdminDashboardServiceImpl(
            UserRepository userRepository,
            ProductRepository productRepository,
            OrderRepository orderRepository,
            CouponRepository couponRepository,
            PromotionRepository promotionRepository
    ) {
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
        this.couponRepository = couponRepository;
        this.promotionRepository = promotionRepository;
    }

    @Override
    public DashboardStatsDTO getDashboardStats() {
        DashboardStatsDTO stats = new DashboardStatsDTO();
        
        stats.setTotalUsers(userRepository.count());
        stats.setActiveUsers(userRepository.countByIsActive(true));
        
        stats.setTotalProducts(productRepository.count());
        stats.setActiveProducts(productRepository.countByIsActive(true));
        stats.setOutOfStockProducts(productRepository.countOutOfStockProducts());
        
        stats.setTotalOrders(orderRepository.count());
        stats.setPendingOrders(orderRepository.countByStatus(ORDER_STATUS_PENDING));
        stats.setProcessingOrders(orderRepository.countByStatus(ORDER_STATUS_PROCESSING));
        stats.setCompletedOrders(orderRepository.countByStatus(ORDER_STATUS_COMPLETED));
        stats.setCancelledOrders(orderRepository.countByStatus(ORDER_STATUS_CANCELLED));
        
        List<Order> allOrders = orderRepository.findAll();
        BigDecimal totalRevenue = allOrders.stream()
                .filter(o -> ORDER_STATUS_COMPLETED.equals(o.getStatus()))
                .map(Order::getTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        stats.setTotalRevenue(totalRevenue);
        
        LocalDateTime today = LocalDateTime.now().withHour(0).withMinute(0).withSecond(0);
        LocalDateTime tomorrow = today.plusDays(1);
        List<Order> todayOrders = orderRepository.findOrdersByDateRange(today, tomorrow);
        stats.setOrdersToday((long) todayOrders.size());
        BigDecimal revenueToday = todayOrders.stream()
                .filter(o -> ORDER_STATUS_COMPLETED.equals(o.getStatus()))
                .map(Order::getTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        stats.setRevenueToday(revenueToday);
        
        LocalDateTime startOfMonth = LocalDateTime.now().withDayOfMonth(1).withHour(0).withMinute(0).withSecond(0);
        List<Order> monthOrders = orderRepository.findOrdersByDateRange(startOfMonth, LocalDateTime.now());
        stats.setOrdersThisMonth((long) monthOrders.size());
        BigDecimal revenueThisMonth = monthOrders.stream()
                .filter(o -> ORDER_STATUS_COMPLETED.equals(o.getStatus()))
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

