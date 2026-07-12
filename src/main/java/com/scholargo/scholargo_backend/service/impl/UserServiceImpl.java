package com.scholargo.scholargo_backend.service.impl;

import com.scholargo.scholargo_backend.dto.request.UpdateUserRequest;
import com.scholargo.scholargo_backend.dto.response.UserResponse;
import com.scholargo.scholargo_backend.entity.User;
import com.scholargo.scholargo_backend.exception.DuplicateResourceException;
import com.scholargo.scholargo_backend.exception.ResourceNotFoundException;
import com.scholargo.scholargo_backend.repository.UserRepository;
import com.scholargo.scholargo_backend.service.UserService;
import com.scholargo.scholargo_backend.wrapper.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public ApiResponse<?> getCurrentUser() {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        String email = authentication.getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found."));

        UserResponse response = UserResponse.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .role(user.getRole().getName())
                .build();

        return ApiResponse.builder()
                .success(true)
                .message("Current user fetched successfully.")
                .data(response)
                .build();
    }

    @Override
    public ApiResponse<?> updateUser(Long id, UpdateUserRequest request) {

        User user = userRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found."));

        // Check if phone number already exists for another user
        userRepository.findByPhoneNumber(request.getPhoneNumber())
                .ifPresent(existingUser -> {
                    if (!existingUser.getId().equals(id)) {
                        throw new DuplicateResourceException("Phone number already exists.");
                    }
                });

        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setPhoneNumber(request.getPhoneNumber());

        userRepository.save(user);

        UserResponse response = UserResponse.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .role(user.getRole().getName())
                .build();

        return ApiResponse.builder()
                .success(true)
                .message("User updated successfully.")
                .data(response)
                .build();
    }
}