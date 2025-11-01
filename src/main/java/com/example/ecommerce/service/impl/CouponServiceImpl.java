package com.example.ecommerce.service.impl;

import com.example.ecommerce.dto.CouponDTO;
import com.example.ecommerce.dto.CouponValidationResultDTO;
import com.example.ecommerce.exception.CouponNotFoundException;
import com.example.ecommerce.model.Coupon;
import com.example.ecommerce.model.CouponUsage;
import com.example.ecommerce.repository.CouponRepository;
import com.example.ecommerce.repository.CouponUsageRepository;
import com.example.ecommerce.service.CouponService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class CouponServiceImpl implements CouponService {
    private final CouponRepository couponRepository;
    private final CouponUsageRepository couponUsageRepository;

    public CouponServiceImpl(CouponRepository couponRepository, CouponUsageRepository couponUsageRepository) {
        this.couponRepository = couponRepository;
        this.couponUsageRepository = couponUsageRepository;
    }

    @Override
    public List<Coupon> getAllCoupons() {
        return couponRepository.findAll();
    }

    @Override
    @SuppressWarnings("null")
    public @NonNull Coupon getCouponById(@NonNull Long id) {
        return couponRepository.findById(id)
                .orElseThrow(() -> new CouponNotFoundException(id));
    }

    @Override
    public Coupon createCoupon(CouponDTO couponDTO) {
        Coupon coupon = new Coupon();
        coupon.setCode(couponDTO.getCode().toUpperCase());
        coupon.setDescription(couponDTO.getDescription());
        coupon.setDiscountType(couponDTO.getDiscountType());
        coupon.setDiscountValue(couponDTO.getDiscountValue());
        coupon.setMinPurchaseAmount(couponDTO.getMinPurchaseAmount());
        coupon.setMaxDiscountAmount(couponDTO.getMaxDiscountAmount());
        coupon.setStartDate(couponDTO.getStartDate());
        coupon.setExpiryDate(couponDTO.getExpiryDate());
        coupon.setUsageLimit(couponDTO.getUsageLimit());
        coupon.setUsagePerUser(couponDTO.getUsagePerUser());
        coupon.setIsActive(couponDTO.getIsActive() != null ? couponDTO.getIsActive() : Boolean.TRUE);
        coupon.setApplicableTo(couponDTO.getApplicableTo());
        coupon.setApplicableCategoryIds(couponDTO.getApplicableCategoryIds());
        coupon.setApplicableProductIds(couponDTO.getApplicableProductIds());
        coupon.setUsageCount(0);
        
        return couponRepository.save(coupon);
    }

    @Override
    @SuppressWarnings("null")
    public @NonNull Coupon updateCoupon(@NonNull Long id, CouponDTO couponDTO) {
        Coupon coupon = getCouponById(id);
        
        if (couponDTO.getCode() != null) coupon.setCode(couponDTO.getCode().toUpperCase());
        if (couponDTO.getDescription() != null) coupon.setDescription(couponDTO.getDescription());
        if (couponDTO.getDiscountType() != null) coupon.setDiscountType(couponDTO.getDiscountType());
        if (couponDTO.getDiscountValue() != null) coupon.setDiscountValue(couponDTO.getDiscountValue());
        if (couponDTO.getMinPurchaseAmount() != null) coupon.setMinPurchaseAmount(couponDTO.getMinPurchaseAmount());
        if (couponDTO.getMaxDiscountAmount() != null) coupon.setMaxDiscountAmount(couponDTO.getMaxDiscountAmount());
        if (couponDTO.getStartDate() != null) coupon.setStartDate(couponDTO.getStartDate());
        if (couponDTO.getExpiryDate() != null) coupon.setExpiryDate(couponDTO.getExpiryDate());
        if (couponDTO.getUsageLimit() != null) coupon.setUsageLimit(couponDTO.getUsageLimit());
        if (couponDTO.getUsagePerUser() != null) coupon.setUsagePerUser(couponDTO.getUsagePerUser());
        if (couponDTO.getIsActive() != null) coupon.setIsActive(couponDTO.getIsActive());
        if (couponDTO.getApplicableTo() != null) coupon.setApplicableTo(couponDTO.getApplicableTo());
        if (couponDTO.getApplicableCategoryIds() != null) coupon.setApplicableCategoryIds(couponDTO.getApplicableCategoryIds());
        if (couponDTO.getApplicableProductIds() != null) coupon.setApplicableProductIds(couponDTO.getApplicableProductIds());
        
        return couponRepository.save(coupon);
    }

    @Override
    public void deleteCoupon(@NonNull Long id) {
        couponRepository.delete(getCouponById(id));
    }

    @Override
    public Coupon findByCode(String code) {
        return couponRepository.findByCode(code.toUpperCase())
                .orElseThrow(() -> new CouponNotFoundException("Coupon not found with code: " + code));
    }

    @Override
    public List<Coupon> getActiveCoupons() {
        return couponRepository.findByIsActive(true);
    }

    @Override
    public List<Coupon> getCouponsByType(String discountType) {
        return couponRepository.findByDiscountType(discountType);
    }

    @Override
    public Page<Coupon> getAllCouponsPaginated(@NonNull Pageable pageable) {
        return couponRepository.findAll(pageable);
    }

    @Override
    public CouponValidationResultDTO validateCoupon(String code, Long userId, BigDecimal cartTotal) {
        Coupon coupon = couponRepository.findValidCoupon(code.toUpperCase(), LocalDateTime.now())
                .orElse(null);

        if (coupon == null) {
            return new CouponValidationResultDTO(false, "Invalid or expired coupon", BigDecimal.ZERO, cartTotal, code);
        }

        if (coupon.getMinPurchaseAmount() != null && cartTotal.compareTo(coupon.getMinPurchaseAmount()) < 0) {
            return new CouponValidationResultDTO(
                false, 
                "Minimum purchase amount of " + coupon.getMinPurchaseAmount() + " required", 
                BigDecimal.ZERO, 
                cartTotal, 
                code
            );
        }

        if (coupon.getUsagePerUser() != null) {
            long userUsageCount = couponUsageRepository.countByCouponIdAndUserId(coupon.getId(), userId);
            if (userUsageCount >= coupon.getUsagePerUser()) {
                return new CouponValidationResultDTO(false, "Coupon usage limit exceeded for this user", BigDecimal.ZERO, cartTotal, code);
            }
        }

        BigDecimal discountAmount = calculateDiscount(coupon, cartTotal);
        BigDecimal finalAmount = cartTotal.subtract(discountAmount);

        return new CouponValidationResultDTO(true, "Coupon is valid", discountAmount, finalAmount, code);
    }

    @Override
    public BigDecimal calculateDiscount(Coupon coupon, BigDecimal amount) {
        BigDecimal discount;
        
        if ("PERCENTAGE".equals(coupon.getDiscountType())) {
            discount = amount.multiply(coupon.getDiscountValue())
                    .divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP);
            
            if (coupon.getMaxDiscountAmount() != null && discount.compareTo(coupon.getMaxDiscountAmount()) > 0) {
                discount = coupon.getMaxDiscountAmount();
            }
        } else if ("FIXED".equals(coupon.getDiscountType())) {
            discount = coupon.getDiscountValue();
            
            if (discount.compareTo(amount) > 0) {
                discount = amount;
            }
        } else {
            discount = BigDecimal.ZERO;
        }
        
        return discount;
    }

    @Override
    public void applyCoupon(String code, Long userId, Long orderId, BigDecimal discountAmount) {
        Coupon coupon = findByCode(code);
        
        CouponUsage usage = new CouponUsage();
        usage.setCouponId(coupon.getId());
        usage.setUserId(userId);
        usage.setOrderId(orderId);
        usage.setDiscountAmount(discountAmount);
        couponUsageRepository.save(usage);
        
        coupon.setUsageCount(coupon.getUsageCount() + 1);
        couponRepository.save(coupon);
    }

    @Override
    public void activateCoupon(@NonNull Long id) {
        Coupon coupon = getCouponById(id);
        coupon.setIsActive(true);
        couponRepository.save(coupon);
    }

    @Override
    public void deactivateCoupon(@NonNull Long id) {
        Coupon coupon = getCouponById(id);
        coupon.setIsActive(false);
        couponRepository.save(coupon);
    }

    @Override
    public List<CouponUsage> getCouponUsageHistory(Long couponId) {
        return couponUsageRepository.findByCouponId(couponId);
    }

    @Override
    public List<CouponUsage> getUserCouponUsage(Long userId) {
        return couponUsageRepository.findByUserId(userId);
    }

    @Override
    public long getCouponUsageCount(Long couponId) {
        return couponUsageRepository.countByCouponId(couponId);
    }

    @Override
    public long getUserCouponUsageCount(Long couponId, Long userId) {
        return couponUsageRepository.countByCouponIdAndUserId(couponId, userId);
    }
}
 
