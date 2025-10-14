package com.example.ecommerce.repository;

import com.example.ecommerce.model.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface CouponRepository extends JpaRepository<Coupon, Long> {
    Optional<Coupon> findByCode(String code);
    
    List<Coupon> findByIsActive(Boolean isActive);
    
    @Query("SELECT c FROM Coupon c WHERE c.isActive = true AND " +
           "c.code = :code AND " +
           "(c.startDate IS NULL OR c.startDate <= :now) AND " +
           "(c.expiryDate IS NULL OR c.expiryDate >= :now) AND " +
           "(c.usageLimit IS NULL OR c.usageCount < c.usageLimit)")
    Optional<Coupon> findValidCoupon(@Param("code") String code, @Param("now") LocalDateTime now);
    
    List<Coupon> findByDiscountType(String discountType);
}
 
