package com.example.ecommerce.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "payments")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Long orderId;

    private LocalDateTime paymentDate;

    @DecimalMin("0.0")
    private BigDecimal amount;

    @Size(max = 30)
    private String method;

    @Size(max = 30)
    private String status;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getOrderId() { return orderId; }
    public void setOrderId(Long orderId) { this.orderId = orderId; }
    public java.time.LocalDateTime getPaymentDate() { return paymentDate; }
    public void setPaymentDate(java.time.LocalDateTime paymentDate) { this.paymentDate = paymentDate; }
    public java.math.BigDecimal getAmount() { return amount; }
    public void setAmount(java.math.BigDecimal amount) { this.amount = amount; }
    public String getMethod() { return method; }
    public void setMethod(String method) { this.method = method; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
} 
