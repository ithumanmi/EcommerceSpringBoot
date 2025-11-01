package com.example.ecommerce.service;

import com.example.ecommerce.dto.CouponDTO;
import com.example.ecommerce.dto.CouponValidationResultDTO;
import com.example.ecommerce.model.Coupon;
import com.example.ecommerce.model.CouponUsage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.NonNull;

import java.math.BigDecimal;
import java.util.List;

public interface CouponService {
    List<Coupon> getAllCoupons();
    @NonNull Coupon getCouponById(@NonNull Long id);
    Coupon createCoupon(CouponDTO couponDTO);
    @NonNull Coupon updateCoupon(@NonNull Long id, CouponDTO couponDTO);
    void deleteCoupon(@NonNull Long id);
    
    Coupon findByCode(String code);
    List<Coupon> getActiveCoupons();
    List<Coupon> getCouponsByType(String discountType);
    Page<Coupon> getAllCouponsPaginated(@NonNull Pageable pageable);
    
    CouponValidationResultDTO validateCoupon(String code, Long userId, BigDecimal cartTotal);
    BigDecimal calculateDiscount(Coupon coupon, BigDecimal amount);
    void applyCoupon(String code, Long userId, Long orderId, BigDecimal discountAmount);
    
    void activateCoupon(@NonNull Long id);
    void deactivateCoupon(@NonNull Long id);
    
    List<CouponUsage> getCouponUsageHistory(Long couponId);
    List<CouponUsage> getUserCouponUsage(Long userId);
    long getCouponUsageCount(Long couponId);
    long getUserCouponUsageCount(Long couponId, Long userId);
}
 
