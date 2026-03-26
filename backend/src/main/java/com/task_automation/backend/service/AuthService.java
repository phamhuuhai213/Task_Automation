package com.task_automation.backend.service;

import com.task_automation.backend.dto.request.LoginRequest;
import com.task_automation.backend.dto.request.RegisterRequest;
import com.task_automation.backend.dto.response.AuthResponse;
import com.task_automation.backend.dto.response.UserResponse;

public interface AuthService {
    UserResponse register(RegisterRequest request);
    AuthResponse login(LoginRequest request);
}
