package com.example.funchatbe.service;

import com.example.funchatbe.entity.User;
import com.example.funchatbe.entity.request.LoginRequest;
import com.example.funchatbe.exception.BadRequestException;
import com.example.funchatbe.exception.NotFoundException;
import com.example.funchatbe.mapper.UserMapper;
import com.example.funchatbe.repository.UserRepository;
import com.example.funchatbe.response.AuthResponse;
import com.example.funchatbe.securiry.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private CustomUserDetailService customUserDetailService;
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private UserRepository userRepository;


    public AuthResponse login(LoginRequest request) {

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                request.getEmail(),
                request.getPassword()
        );

        try {

            Authentication authentication = authenticationManager.authenticate(token);

            SecurityContextHolder.getContext().setAuthentication(authentication);

            UserDetails userDetails = customUserDetailService.loadUserByUsername(authentication.getName());

            String jwtToken = jwtUtils.generateToken(userDetails);

            User user = userRepository.findUsersByEmail(authentication.getName()).orElseThrow(() -> new NotFoundException("Not Found User"));

            return new AuthResponse(
                    UserMapper.toUserDto(user),
                    jwtToken,
                    true
            );
        } catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }
    }
}
