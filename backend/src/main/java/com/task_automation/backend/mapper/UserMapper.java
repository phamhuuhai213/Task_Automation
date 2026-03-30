package com.task_automation.backend.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import com.task_automation.backend.dto.response.UserResponse;
import com.task_automation.backend.entity.User;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {
    UserResponse toUserResponse(User user);

    List<UserResponse> toUserResponseList(List<User> users);
}
