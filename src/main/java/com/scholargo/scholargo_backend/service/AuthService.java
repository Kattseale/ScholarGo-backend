package com.scholargo.scholargo_backend.service;

import com.scholargo.scholargo_backend.dto.request.LoginRequest;
import com.scholargo.scholargo_backend.dto.request.RegisterRequest;
import com.scholargo.scholargo_backend.wrapper.ApiResponse;

public interface AuthService {

    ApiResponse<?> register(RegisterRequest request);

    ApiResponse<?> login(LoginRequest request);

}