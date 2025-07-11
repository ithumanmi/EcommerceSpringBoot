package com.example.ecommerce.controller;

import com.example.ecommerce.model.Coupon;
import com.example.ecommerce.service.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/coupons")
public class CouponController {
    @Autowired
    private CouponService couponService;

    @GetMapping
    public List<Coupon> getAllCoupons() {
        return couponService.getAllCoupons();
    }

    @GetMapping("/{id}")
    public Coupon getCouponById(@PathVariable Long id) {
        return couponService.getCouponById(id);
    }

    @PostMapping
    public Coupon createCoupon(@RequestBody Coupon coupon) {
        return couponService.createCoupon(coupon);
    }
} 
