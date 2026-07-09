package com.ecommerce.backend.controller;

import com.ecommerce.backend.entity.Coupon;
import com.ecommerce.backend.service.CouponService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/coupons")
public class CouponController {
    private final CouponService couponService;

    public CouponController(CouponService couponService) {
        this.couponService = couponService;
    }

    @PostMapping
    public Coupon createCoupon(@RequestBody Coupon coupon){
        return  couponService.createCoupon(coupon);
    }

    @GetMapping
    public List<Coupon> getAllCoupons(){
        return couponService.getAllCoupons();
    }

    @GetMapping("/validate/{code}")
    public Coupon validateCoupon(@PathVariable String code){
        return couponService.validateCoupon(code);
    }

    @DeleteMapping("/{id}")
    public String deleteCoupon(@PathVariable Long id ){
        couponService.deleteCoupon(id);
        return  " Coupon Deleted Successfully ";
    }
}

