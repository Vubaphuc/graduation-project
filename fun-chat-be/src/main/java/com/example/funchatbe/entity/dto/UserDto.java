package com.example.funchatbe.entity.dto;

import com.example.funchatbe.entity.Role;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {
    private Integer id;
    private String fullName;
    private String email;
    private List<Role> roles;
}
