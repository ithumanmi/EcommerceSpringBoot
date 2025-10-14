package com.example.ecommerce.dto;

import java.math.BigDecimal;
import java.util.Map;

public class DashboardStatsDTO {
    private Long totalUsers;
    private Long activeUsers;
    private Long totalProducts;
    private Long activeProducts;
    private Long outOfStockProducts;
    private Long totalOrders;
    private Long pendingOrders;
    private Long processingOrders;
    private Long completedOrders;
    private Long cancelledOrders;
    private BigDecimal totalRevenue;
    private BigDecimal revenueToday;
    private BigDecimal revenueThisMonth;
    private Long ordersToday;
    private Long ordersThisMonth;
    private Long newCustomersToday;
    private Long newCustomersThisMonth;
    private Long activeCoupons;
    private Long activePromotions;
    private Map<String, Long> ordersByStatus;
    private Map<String, BigDecimal> revenueByMonth;

    public Long getTotalUsers() { return totalUsers; }
    public void setTotalUsers(Long totalUsers) { this.totalUsers = totalUsers; }
    
    public Long getActiveUsers() { return activeUsers; }
    public void setActiveUsers(Long activeUsers) { this.activeUsers = activeUsers; }
    
    public Long getTotalProducts() { return totalProducts; }
    public void setTotalProducts(Long totalProducts) { this.totalProducts = totalProducts; }
    
    public Long getActiveProducts() { return activeProducts; }
    public void setActiveProducts(Long activeProducts) { this.activeProducts = activeProducts; }
    
    public Long getOutOfStockProducts() { return outOfStockProducts; }
    public void setOutOfStockProducts(Long outOfStockProducts) { this.outOfStockProducts = outOfStockProducts; }
    
    public Long getTotalOrders() { return totalOrders; }
    public void setTotalOrders(Long totalOrders) { this.totalOrders = totalOrders; }
    
    public Long getPendingOrders() { return pendingOrders; }
    public void setPendingOrders(Long pendingOrders) { this.pendingOrders = pendingOrders; }
    
    public Long getProcessingOrders() { return processingOrders; }
    public void setProcessingOrders(Long processingOrders) { this.processingOrders = processingOrders; }
    
    public Long getCompletedOrders() { return completedOrders; }
    public void setCompletedOrders(Long completedOrders) { this.completedOrders = completedOrders; }
    
    public Long getCancelledOrders() { return cancelledOrders; }
    public void setCancelledOrders(Long cancelledOrders) { this.cancelledOrders = cancelledOrders; }
    
    public BigDecimal getTotalRevenue() { return totalRevenue; }
    public void setTotalRevenue(BigDecimal totalRevenue) { this.totalRevenue = totalRevenue; }
    
    public BigDecimal getRevenueToday() { return revenueToday; }
    public void setRevenueToday(BigDecimal revenueToday) { this.revenueToday = revenueToday; }
    
    public BigDecimal getRevenueThisMonth() { return revenueThisMonth; }
    public void setRevenueThisMonth(BigDecimal revenueThisMonth) { this.revenueThisMonth = revenueThisMonth; }
    
    public Long getOrdersToday() { return ordersToday; }
    public void setOrdersToday(Long ordersToday) { this.ordersToday = ordersToday; }
    
    public Long getOrdersThisMonth() { return ordersThisMonth; }
    public void setOrdersThisMonth(Long ordersThisMonth) { this.ordersThisMonth = ordersThisMonth; }
    
    public Long getNewCustomersToday() { return newCustomersToday; }
    public void setNewCustomersToday(Long newCustomersToday) { this.newCustomersToday = newCustomersToday; }
    
    public Long getNewCustomersThisMonth() { return newCustomersThisMonth; }
    public void setNewCustomersThisMonth(Long newCustomersThisMonth) { this.newCustomersThisMonth = newCustomersThisMonth; }
    
    public Long getActiveCoupons() { return activeCoupons; }
    public void setActiveCoupons(Long activeCoupons) { this.activeCoupons = activeCoupons; }
    
    public Long getActivePromotions() { return activePromotions; }
    public void setActivePromotions(Long activePromotions) { this.activePromotions = activePromotions; }
    
    public Map<String, Long> getOrdersByStatus() { return ordersByStatus; }
    public void setOrdersByStatus(Map<String, Long> ordersByStatus) { this.ordersByStatus = ordersByStatus; }
    
    public Map<String, BigDecimal> getRevenueByMonth() { return revenueByMonth; }
    public void setRevenueByMonth(Map<String, BigDecimal> revenueByMonth) { this.revenueByMonth = revenueByMonth; }
}

