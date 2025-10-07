package com.example.ecommerce.dto;

import java.time.LocalDateTime;

/**
 * Data Transfer Object for Shipping
 */
public class ShippingDTO {
    private Long id;
    private Long orderId;
    private String shippingAddress;
    private LocalDateTime shippingDate;
    private String status;
    private String trackingNumber;
    private String carrier;

    // Constructors
    public ShippingDTO() {}

    public ShippingDTO(Long id, Long orderId, String shippingAddress, LocalDateTime shippingDate, String status) {
        this.id = id;
        this.orderId = orderId;
        this.shippingAddress = shippingAddress;
        this.shippingDate = shippingDate;
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

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public LocalDateTime getShippingDate() {
        return shippingDate;
    }

    public void setShippingDate(LocalDateTime shippingDate) {
        this.shippingDate = shippingDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTrackingNumber() {
        return trackingNumber;
    }

    public void setTrackingNumber(String trackingNumber) {
        this.trackingNumber = trackingNumber;
    }

    public String getCarrier() {
        return carrier;
    }

    public void setCarrier(String carrier) {
        this.carrier = carrier;
    }
}
