package com.task_automation.backend.dto.response;

import java.time.LocalDateTime;

import com.task_automation.backend.enums.Role;

public record UserResponse(
        Long id,
        String email,
        String fullName,
        Role role,
        LocalDateTime createdAt) {
}
