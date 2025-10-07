package com.example.ecommerce.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Data Transfer Object for Payment
 */
public class PaymentDTO {
    private Long id;
    private Long orderId;
    private LocalDateTime paymentDate;
    private BigDecimal amount;
    private String method;
    private String status;
    private String transactionId;

    // Constructors
    public PaymentDTO() {}

    public PaymentDTO(Long id, Long orderId, LocalDateTime paymentDate, BigDecimal amount, String method, String status) {
        this.id = id;
        this.orderId = orderId;
        this.paymentDate = paymentDate;
        this.amount = amount;
        this.method = method;
        this.status = status;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public LocalDateTime getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDateTime paymentDate) {
        this.paymentDate = paymentDate;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }
}
