package com.example.ecommerce.service;

import com.example.ecommerce.model.Payment;
import java.util.List;

public interface PaymentService {
    List<Payment> getAllPayments();
    Payment getPaymentById(Long id);
    Payment createPayment(Payment payment);
} 
