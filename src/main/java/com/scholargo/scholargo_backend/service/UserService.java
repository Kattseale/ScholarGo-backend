package com.scholargo.scholargo_backend.service;

import com.scholargo.scholargo_backend.dto.request.UpdateUserRequest;
import com.scholargo.scholargo_backend.wrapper.ApiResponse;

public interface UserService {

    ApiResponse<?> getCurrentUser();

    ApiResponse<?> updateUser(Long id, UpdateUserRequest request);

}