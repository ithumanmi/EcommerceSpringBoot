package com.example.ecommerce.controller;

import com.example.ecommerce.dto.ApiResponse;
import com.example.ecommerce.dto.CouponDTO;
import com.example.ecommerce.dto.CouponValidationResultDTO;
import com.example.ecommerce.dto.ValidateCouponDTO;
import com.example.ecommerce.model.Coupon;
import com.example.ecommerce.model.CouponUsage;
import com.example.ecommerce.model.User;
import com.example.ecommerce.service.CouponService;
import com.example.ecommerce.service.UserService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/coupons")
@CrossOrigin(origins = "*")
public class CouponController {
    private final CouponService couponService;
    private final UserService userService;

    public CouponController(CouponService couponService, UserService userService) {
        this.couponService = couponService;
        this.userService = userService;
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Coupon>> getAllCoupons() {
        List<Coupon> coupons = couponService.getAllCoupons();
        return ResponseEntity.ok(coupons);
    }

    @GetMapping("/paginated")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Page<Coupon>> getCouponsPaginated(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "createdAt") String sortBy,
            @RequestParam(defaultValue = "DESC") String sortDir
    ) {
        Sort sort = sortDir.equalsIgnoreCase("DESC") 
            ? Sort.by(sortBy).descending() 
            : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return ResponseEntity.ok(couponService.getAllCouponsPaginated(pageable));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Coupon> getCouponById(@PathVariable Long id) {
        Coupon coupon = couponService.getCouponById(id);
        return ResponseEntity.ok(coupon);
    }

    @GetMapping("/code/{code}")
    public ResponseEntity<Coupon> getCouponByCode(@PathVariable String code) {
        Coupon coupon = couponService.findByCode(code);
        return ResponseEntity.ok(coupon);
    }

    @GetMapping("/active")
    public ResponseEntity<List<Coupon>> getActiveCoupons() {
        List<Coupon> coupons = couponService.getActiveCoupons();
        return ResponseEntity.ok(coupons);
    }

    @GetMapping("/type/{discountType}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Coupon>> getCouponsByType(@PathVariable String discountType) {
        List<Coupon> coupons = couponService.getCouponsByType(discountType);
        return ResponseEntity.ok(coupons);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Coupon> createCoupon(@Valid @RequestBody CouponDTO couponDTO) {
        Coupon coupon = couponService.createCoupon(couponDTO);
        return ResponseEntity.ok(coupon);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Coupon> updateCoupon(
            @PathVariable Long id,
            @Valid @RequestBody CouponDTO couponDTO
    ) {
        Coupon coupon = couponService.updateCoupon(id, couponDTO);
        return ResponseEntity.ok(coupon);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<Void>> deleteCoupon(@PathVariable Long id) {
        couponService.deleteCoupon(id);
        return ResponseEntity.ok(new ApiResponse<>(true, "Coupon deleted successfully"));
    }

    @PostMapping("/validate")
    public ResponseEntity<CouponValidationResultDTO> validateCoupon(
            @Valid @RequestBody ValidateCouponDTO validateCouponDTO,
            Authentication authentication
    ) {
        String username = authentication.getName();
        User user = userService.findByUsername(username);
        CouponValidationResultDTO result = couponService.validateCoupon(
            validateCouponDTO.getCode(),
            user.getId(),
            validateCouponDTO.getCartTotal()
        );
        return ResponseEntity.ok(result);
    }

    @PutMapping("/{id}/activate")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<Void>> activateCoupon(@PathVariable Long id) {
        couponService.activateCoupon(id);
        return ResponseEntity.ok(new ApiResponse<>(true, "Coupon activated successfully"));
    }

    @PutMapping("/{id}/deactivate")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<Void>> deactivateCoupon(@PathVariable Long id) {
        couponService.deactivateCoupon(id);
        return ResponseEntity.ok(new ApiResponse<>(true, "Coupon deactivated successfully"));
    }

    @GetMapping("/{id}/usage")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<CouponUsage>> getCouponUsageHistory(@PathVariable Long id) {
        List<CouponUsage> usage = couponService.getCouponUsageHistory(id);
        return ResponseEntity.ok(usage);
    }

    @GetMapping("/my-usage")
    public ResponseEntity<List<CouponUsage>> getMyCouponUsage(Authentication authentication) {
        String username = authentication.getName();
        User user = userService.findByUsername(username);
        List<CouponUsage> usage = couponService.getUserCouponUsage(user.getId());
        return ResponseEntity.ok(usage);
    }

    @GetMapping("/{id}/usage/count")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Map<String, Long>> getCouponUsageCount(@PathVariable Long id) {
        long count = couponService.getCouponUsageCount(id);
        Map<String, Long> response = new HashMap<>();
        response.put("usageCount", count);
        return ResponseEntity.ok(response);
    }
}
 
