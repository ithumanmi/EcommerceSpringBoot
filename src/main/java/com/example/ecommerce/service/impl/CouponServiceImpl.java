package com.example.ecommerce.service.impl;

import com.example.ecommerce.model.Coupon;
import com.example.ecommerce.repository.CouponRepository;
import com.example.ecommerce.service.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CouponServiceImpl implements CouponService {
    @Autowired
    private CouponRepository couponRepository;

    @Override
    public List<Coupon> getAllCoupons() {
        return couponRepository.findAll();
    }

    @Override
    public Coupon getCouponById(Long id) {
        return couponRepository.findById(id).orElse(null);
    }

    @Override
    public Coupon createCoupon(Coupon coupon) {
        return couponRepository.save(coupon);
    }
} 
