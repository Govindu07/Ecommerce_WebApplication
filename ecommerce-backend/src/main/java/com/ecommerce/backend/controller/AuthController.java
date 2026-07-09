package com.ecommerce.backend.controller;

import com.ecommerce.backend.dto.*;
import com.ecommerce.backend.entity.User;
import com.ecommerce.backend.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    // REGISTER API
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authService.register(request));
    }

    // LOGIN API
    @PostMapping("/login")
    public ResponseEntity<User> login(
            @RequestBody LoginRequest request
    ) {

        System.out.println("LOGIN EMAIL = " + request.getEmail());
        System.out.println("LOGIN PASSWORD = " + request.getPassword());

        return ResponseEntity.ok(
                authService.login(request)
        );
    }

}
