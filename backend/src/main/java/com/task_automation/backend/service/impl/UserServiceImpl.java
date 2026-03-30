package com.task_automation.backend.service.impl;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.task_automation.backend.dto.response.UserResponse;
import com.task_automation.backend.entity.User;
import com.task_automation.backend.exception.AppException;
import com.task_automation.backend.mapper.UserMapper;
import com.task_automation.backend.repository.UserRepository;
import com.task_automation.backend.service.UserService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserServiceImpl implements UserService{
    UserRepository userRepository;
    UserMapper userMapper;

    @Override
    public List<UserResponse> getAllUsers() {
        List<User> users = userRepository.findAll();
        return userMapper.toUserResponseList(users);
    }

    @Override
    public UserResponse getUserById(Long id) {
        User user = userRepository.findById(id)
            .orElseThrow(() -> new AppException(HttpStatus.NOT_FOUND, "User not found with id: " + id));

        
        return userMapper.toUserResponse(user);
    }

    @Override
    public void toggleUserStatus(Long id, boolean isActive) {
        User user = userRepository.findById(id)
            .orElseThrow(() -> new AppException(HttpStatus.NOT_FOUND, "User not found with id: " + id));

        user.setIsActive(isActive);
        userRepository.save(user);
    }
    
}
