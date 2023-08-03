package com.example.graduationprojectbe.dto.dto;

import com.example.graduationprojectbe.entity.User;
import jakarta.persistence.Column;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class RoomDto {
    private Integer id;
    private String name;
    private List<UserDto> members;
}
