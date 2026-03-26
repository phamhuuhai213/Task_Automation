package com.task_automation.backend.dto.request;

public record RegisterRequest(
        String email,
        String password,
        String fullName) {
}
