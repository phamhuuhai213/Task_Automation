package com.task_automation.backend.service;

import java.util.List;

import com.task_automation.backend.dto.response.UserResponse;

public interface UserService {
    List<UserResponse> getAllUsers();
    UserResponse getUserById(Long id);
    void toggleUserStatus(Long id, boolean isActive);
}
