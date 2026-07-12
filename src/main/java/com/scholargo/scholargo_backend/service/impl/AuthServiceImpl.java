package com.scholargo.scholargo_backend.service.impl;

import com.scholargo.scholargo_backend.dto.request.LoginRequest;
import com.scholargo.scholargo_backend.dto.request.RegisterRequest;
import com.scholargo.scholargo_backend.dto.response.AuthResponse;
import com.scholargo.scholargo_backend.entity.Role;
import com.scholargo.scholargo_backend.entity.User;
import com.scholargo.scholargo_backend.exception.DuplicateResourceException;
import com.scholargo.scholargo_backend.exception.ResourceNotFoundException;
import com.scholargo.scholargo_backend.repository.RoleRepository;
import com.scholargo.scholargo_backend.repository.UserRepository;
import com.scholargo.scholargo_backend.security.JwtService;
import com.scholargo.scholargo_backend.service.AuthService;
import com.scholargo.scholargo_backend.wrapper.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Override
    public ApiResponse<?> register(RegisterRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new DuplicateResourceException("Email already exists.");
        }

        if (userRepository.existsByPhoneNumber(request.getPhoneNumber())) {
            throw new DuplicateResourceException("Phone number already exists.");
        }

        Role role = roleRepository.findByName(request.getRole().toUpperCase())
                .orElseThrow(() -> new ResourceNotFoundException("Role not found."));

        User user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .phoneNumber(request.getPhoneNumber())
                .password(passwordEncoder.encode(request.getPassword()))
                .enabled(true)
                .role(role)
                .build();

        userRepository.save(user);

        return ApiResponse.builder()
                .success(true)
                .message("User registered successfully.")
                .data(null)
                .build();
    }

    @Override
    public ApiResponse<?> login(LoginRequest request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Invalid email or password."));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new ResourceNotFoundException("Invalid email or password.");
        }

        String token = jwtService.generateToken(user.getEmail());

        AuthResponse authResponse = new AuthResponse(
                token,
                "JWT Token Generated"
        );

        return ApiResponse.builder()
                .success(true)
                .message("Login successful.")
                .data(authResponse)
                .build();
    }
}