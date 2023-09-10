package com.example.funchatbe.response;

import com.example.funchatbe.entity.dto.UserDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponse {
    private UserDto auth;
    private String token;
    @JsonProperty("isAuthenticated")
    private boolean isAuthenticated;
}
