package com.example.ecommerce.service;

import com.example.ecommerce.model.Payment;
import org.springframework.lang.NonNull;

import java.util.List;

public interface PaymentService {
    List<Payment> getAllPayments();
    Payment getPaymentById(@NonNull Long id);
    Payment createPayment(Payment payment);
} 
