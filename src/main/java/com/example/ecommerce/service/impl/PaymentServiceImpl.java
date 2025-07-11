package com.example.ecommerce.service.impl;

import com.example.ecommerce.model.Payment;
import com.example.ecommerce.repository.PaymentRepository;
import com.example.ecommerce.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    private PaymentRepository paymentRepository;

    @Override
    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    @Override
    public Payment getPaymentById(Long id) {
        return paymentRepository.findById(id).orElse(null);
    }

    @Override
    public Payment createPayment(Payment payment) {
        return paymentRepository.save(payment);
    }
} 
