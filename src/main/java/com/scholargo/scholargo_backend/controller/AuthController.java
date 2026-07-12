package com.scholargo.scholargo_backend.controller;

import com.scholargo.scholargo_backend.dto.request.LoginRequest;
import com.scholargo.scholargo_backend.dto.request.RegisterRequest;
import com.scholargo.scholargo_backend.service.AuthService;
import com.scholargo.scholargo_backend.wrapper.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ApiResponse<?> register(@Valid @RequestBody RegisterRequest request) {
        return authService.register(request);
    }

    @PostMapping("/login")
    public ApiResponse<?> login(@Valid @RequestBody LoginRequest request) {
        return authService.login(request);
    }

}