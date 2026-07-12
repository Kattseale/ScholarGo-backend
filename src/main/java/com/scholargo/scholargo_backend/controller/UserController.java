package com.scholargo.scholargo_backend.controller;

import com.scholargo.scholargo_backend.dto.request.UpdateUserRequest;
import com.scholargo.scholargo_backend.service.UserService;
import com.scholargo.scholargo_backend.wrapper.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    /**
     * Get currently logged-in user
     */
    @GetMapping("/me")
    public ApiResponse<?> getCurrentUser() {
        return userService.getCurrentUser();
    }

    /**
     * Update user profile
     */
    @PutMapping("/{id}")
    public ApiResponse<?> updateUser(
            @PathVariable Long id,
            @Valid @RequestBody UpdateUserRequest request) {

        return userService.updateUser(id, request);
    }

}