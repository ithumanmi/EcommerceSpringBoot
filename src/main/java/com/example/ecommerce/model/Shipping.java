package com.example.ecommerce.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "shippings")
public class Shipping {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Long orderId;

    @Size(max = 255)
    private String shippingAddress;

    private LocalDateTime shippingDate;

    @Size(max = 30)
    private String status;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getOrderId() { return orderId; }
    public void setOrderId(Long orderId) { this.orderId = orderId; }
    public String getShippingAddress() { return shippingAddress; }
    public void setShippingAddress(String shippingAddress) { this.shippingAddress = shippingAddress; }
    public java.time.LocalDateTime getShippingDate() { return shippingDate; }
    public void setShippingDate(java.time.LocalDateTime shippingDate) { this.shippingDate = shippingDate; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
} 
