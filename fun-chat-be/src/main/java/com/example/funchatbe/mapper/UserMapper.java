package com.example.funchatbe.mapper;

import com.example.funchatbe.entity.User;
import com.example.funchatbe.entity.dto.UserDto;

public class UserMapper {
    public static UserDto toUserDto(User user) {

        UserDto userDto = new UserDto (
                user.getId(),
                user.getFullName(),
                user.getEmail(),
                user.getRoles()
        );
        return userDto;
    }
}
