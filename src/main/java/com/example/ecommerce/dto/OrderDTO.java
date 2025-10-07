package com.example.ecommerce.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Data Transfer Object for Order
 */
public class OrderDTO {
    private Long id;
    private Long userId;
    private String userName;
    private LocalDateTime orderDate;
    private String status;
    private BigDecimal total;
    private List<OrderItemDTO> orderItems;
    private PaymentDTO payment;
    private ShippingDTO shipping;

    // Constructors
    public OrderDTO() {}

    public OrderDTO(Long id, Long userId, LocalDateTime orderDate, String status, BigDecimal total) {
        this.id = id;
        this.userId = userId;
        this.orderDate = orderDate;
        this.status = status;
        this.total = total;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public List<OrderItemDTO> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItemDTO> orderItems) {
        this.orderItems = orderItems;
    }

    public PaymentDTO getPayment() {
        return payment;
    }

    public void setPayment(PaymentDTO payment) {
        this.payment = payment;
    }

    public ShippingDTO getShipping() {
        return shipping;
    }

    public void setShipping(ShippingDTO shipping) {
        this.shipping = shipping;
    }
}
