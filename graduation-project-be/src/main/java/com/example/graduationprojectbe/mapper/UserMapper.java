package com.example.graduationprojectbe.mapper;


import com.example.graduationprojectbe.dto.dto.UserDto;
import com.example.graduationprojectbe.entity.User;

public class UserMapper {
    public static UserDto toUserDto(User user) {

        UserDto userDto = new UserDto (
                user.getId(),
                user.getEmployeeCode(),
                user.getEmployeeName(),
                user.getEmail(),
                user.getPhoneNumber(),
                user.getAddress(),
                user.getRoles()

        );
        return userDto;
    }
}
