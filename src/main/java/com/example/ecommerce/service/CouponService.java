package com.example.ecommerce.service;

import com.example.ecommerce.dto.CouponDTO;
import com.example.ecommerce.dto.CouponValidationResultDTO;
import com.example.ecommerce.dto.ValidateCouponDTO;
import com.example.ecommerce.model.Coupon;
import com.example.ecommerce.model.CouponUsage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.List;

public interface CouponService {
    List<Coupon> getAllCoupons();
    Coupon getCouponById(Long id);
    Coupon createCoupon(CouponDTO couponDTO);
    Coupon updateCoupon(Long id, CouponDTO couponDTO);
    void deleteCoupon(Long id);
    
    Coupon findByCode(String code);
    List<Coupon> getActiveCoupons();
    List<Coupon> getCouponsByType(String discountType);
    Page<Coupon> getAllCouponsPaginated(Pageable pageable);
    
    CouponValidationResultDTO validateCoupon(String code, Long userId, BigDecimal cartTotal);
    BigDecimal calculateDiscount(Coupon coupon, BigDecimal amount);
    void applyCoupon(String code, Long userId, Long orderId, BigDecimal discountAmount);
    
    void activateCoupon(Long id);
    void deactivateCoupon(Long id);
    
    List<CouponUsage> getCouponUsageHistory(Long couponId);
    List<CouponUsage> getUserCouponUsage(Long userId);
    long getCouponUsageCount(Long couponId);
    long getUserCouponUsageCount(Long couponId, Long userId);
}
 
