package com.example.ecommerce.repository;

import com.example.ecommerce.model.CouponUsage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CouponUsageRepository extends JpaRepository<CouponUsage, Long> {
    List<CouponUsage> findByCouponId(Long couponId);
    List<CouponUsage> findByUserId(Long userId);
    List<CouponUsage> findByCouponIdAndUserId(Long couponId, Long userId);
    long countByCouponId(Long couponId);
    long countByCouponIdAndUserId(Long couponId, Long userId);
}

