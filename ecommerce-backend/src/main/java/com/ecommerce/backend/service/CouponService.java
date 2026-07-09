package com.ecommerce.backend.service;

import com.ecommerce.backend.entity.Coupon;
import com.ecommerce.backend.repository.CouponRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class CouponService {

    private final CouponRepository couponRepository;

    public CouponService(CouponRepository couponRepository) {
        this.couponRepository = couponRepository;
    }

    public Coupon createCoupon(Coupon coupon){
        return couponRepository.save(coupon);
    }

    public List<Coupon> getAllCoupons(){
        return  couponRepository.findAll();
    }

    public Coupon validateCoupon(String code){
        Coupon coupon= (Coupon) couponRepository.findByCode(code).orElseThrow(()->new RuntimeException(" Coupon Not Found "));
        if(!coupon.getActive()){
            throw new RuntimeException(" Code is not Active ");
        }
        if(coupon.getExpiryDate().isBefore(LocalDate.now())){
            throw new RuntimeException(" coupon was expired !");
        }
        return coupon;
    }
    public void deleteCoupon(Long id){
        couponRepository.deleteById(id);
    }

}
