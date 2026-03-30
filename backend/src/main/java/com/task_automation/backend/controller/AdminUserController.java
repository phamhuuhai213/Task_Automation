package com.task_automation.backend.controller;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.task_automation.backend.dto.response.ApiResponse;
import com.task_automation.backend.dto.response.UserResponse;
import com.task_automation.backend.service.UserService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/admin/users")
@PreAuthorize("hasRole('ADMIN')")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AdminUserController {
    UserService userService;

    @GetMapping
    public ApiResponse<List<UserResponse>> getAllUsers() {
        return ApiResponse.success("Get successful user list.", userService.getAllUsers());
    }

    @GetMapping("/{id}")
    public ApiResponse<UserResponse> getUserById(@PathVariable Long id) {
        return ApiResponse.success("Get User Info Success.", userService.getUserById(id));
    }
    
    @PatchMapping("/{id}/status")
    public ApiResponse<Void> toggleUserStatus(@PathVariable Long id, @RequestParam boolean isActive) {
        userService.toggleUserStatus(id, isActive);
        String message = isActive ? "Account Unlocked." : "Account Locked";
        return ApiResponse.success(message, null);
    }
}
