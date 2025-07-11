package com.example.ecommerce.repository;

import com.example.ecommerce.model.Shipping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShippingRepository extends JpaRepository<Shipping, Long> {
} 
