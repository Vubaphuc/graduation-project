package com.example.graduationprojectbe.service.auth;

import com.example.graduationprojectbe.entity.User;
import com.example.graduationprojectbe.exception.BadRequestException;
import com.example.graduationprojectbe.exception.NotFoundException;
import com.example.graduationprojectbe.mapper.UserMapper;
import com.example.graduationprojectbe.repository.UserRepository;
import com.example.graduationprojectbe.request.other.LoginRequest;
import com.example.graduationprojectbe.response.AuthResponse;
import com.example.graduationprojectbe.sercurity.JwtUtils;
import com.example.graduationprojectbe.service.CustomUserDetailsService;
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
    private CustomUserDetailsService customUserDetailsService;
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private UserRepository userRepository;

    public AuthResponse login(LoginRequest request) {

        // Tạo đối tượng xác thực
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                request.getEmail(),
                request.getPassword()
        );

        try {
            // Tiến hành xác thực
            Authentication authentication = authenticationManager.authenticate(token);

            // Lưu dữ liệu vào context
            SecurityContextHolder.getContext().setAuthentication(authentication);

            // Lấy ra thông tin của user
            UserDetails userDetails = customUserDetailsService.loadUserByUsername(authentication.getName());

            // Tạo ra token
            String jwtToken = jwtUtils.generateToken(userDetails);

            // Tìm kiếm user
            User user = userRepository.findUsersByEmail(authentication.getName()).orElseThrow(() ->
                    new NotFoundException("Not found user"));

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
