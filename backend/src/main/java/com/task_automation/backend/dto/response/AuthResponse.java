package com.task_automation.backend.dto.response;

public record AuthResponse(
    String accessToken,
    UserResponse user
) {}
