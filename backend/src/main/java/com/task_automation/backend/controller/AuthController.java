package com.task_automation.backend.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.task_automation.backend.dto.request.LoginRequest;
import com.task_automation.backend.dto.request.RegisterRequest;
import com.task_automation.backend.dto.response.ApiResponse;
import com.task_automation.backend.dto.response.AuthResponse;
import com.task_automation.backend.dto.response.UserResponse;
import com.task_automation.backend.service.AuthService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthController {
    AuthService authService;

    @PostMapping("/register")
    public ApiResponse<UserResponse> register(@RequestBody RegisterRequest request) {
        UserResponse user = authService.register(request);
        return ApiResponse.success(
                "Account registration successful!",
                user);
    }

    @PostMapping("/login")
    public ApiResponse<AuthResponse> login(@RequestBody LoginRequest request) {
        return ApiResponse.success(
                "Login successful!",
                authService.login(request));
    }

}
