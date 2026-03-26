package com.task_automation.backend.dto.request;

public record LoginRequest(
        String email,
        String password) {
}
