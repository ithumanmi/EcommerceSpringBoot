package com.example.ecommerce.service;

import com.example.ecommerce.model.Coupon;
import java.util.List;

public interface CouponService {
    List<Coupon> getAllCoupons();
    Coupon getCouponById(Long id);
    Coupon createCoupon(Coupon coupon);
} 
