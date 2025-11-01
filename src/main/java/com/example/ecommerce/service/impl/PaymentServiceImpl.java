package com.example.ecommerce.service.impl;

import com.example.ecommerce.model.Payment;
import com.example.ecommerce.repository.PaymentRepository;
import com.example.ecommerce.service.PaymentService;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepository paymentRepository;

    public PaymentServiceImpl(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Override
    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    @Override
    public Payment getPaymentById(@NonNull Long id) {
        return paymentRepository.findById(id).orElse(null);
    }

    @Override
    @SuppressWarnings("null")
    public @NonNull Payment createPayment(Payment payment) {
        return paymentRepository.save(payment);
    }
} 
