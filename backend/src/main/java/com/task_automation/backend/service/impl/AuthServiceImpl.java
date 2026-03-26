package com.task_automation.backend.service.impl;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.task_automation.backend.dto.request.RegisterRequest;
import com.task_automation.backend.dto.response.UserResponse;
import com.task_automation.backend.entity.User;
import com.task_automation.backend.enums.AuthProvider;
import com.task_automation.backend.enums.Role;
import com.task_automation.backend.exception.AppException;
import com.task_automation.backend.mapper.UserMapper;
import com.task_automation.backend.repository.UserRepository;
import com.task_automation.backend.service.AuthService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthServiceImpl implements AuthService {
    UserRepository userRepository;
    PasswordEncoder passwordEncoder;
    UserMapper userMapper;

    @Override
    public UserResponse register(RegisterRequest request) {
        if (userRepository.existsByEmail(request.email())) {
            throw new AppException(HttpStatus.BAD_REQUEST, "Email already used!");
        }

        User user = User.builder()
                .email(request.email())
                .passwordHash(passwordEncoder.encode(request.password()))
                .fullName(request.fullName())
                .role(Role.USER)
                .authProvider(AuthProvider.LOCAL)
                .isActive(true)
                .build();

        User saveUser = userRepository.save(user);
        return userMapper.toUserResponse(saveUser);
    }

}
