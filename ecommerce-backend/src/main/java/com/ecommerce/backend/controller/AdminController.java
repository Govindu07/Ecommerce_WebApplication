package com.ecommerce.backend.controller;

import com.ecommerce.backend.service.AdminService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/dashboard")
    public Map<String ,Long> getDashboard(){
        Map<String,Long> data=new HashMap<>();
        data.put("totalUsers",adminService.totalUsers());
        data.put("totalProducts",adminService.totalProducts());
        data.put("totalOrders",adminService.totalOrders());
        data.put("totalPayments",adminService.totalPayments());
        return data;
    }
}
