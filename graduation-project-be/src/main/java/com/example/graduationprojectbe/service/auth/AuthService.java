package com.example.graduationprojectbe.service.auth;

import com.example.graduationprojectbe.dto.dto.UserDto;
import com.example.graduationprojectbe.entity.Image;
import com.example.graduationprojectbe.entity.User;
import com.example.graduationprojectbe.exception.BadRequestException;
import com.example.graduationprojectbe.exception.NotFoundException;
import com.example.graduationprojectbe.mapper.DataMapper;
import com.example.graduationprojectbe.mapper.UserMapper;
import com.example.graduationprojectbe.repository.ImageRepository;
import com.example.graduationprojectbe.repository.UserRepository;
import com.example.graduationprojectbe.request.ChangePasswordRequest;
import com.example.graduationprojectbe.request.ForgotPasswordRequest;
import com.example.graduationprojectbe.request.LoginRequest;
import com.example.graduationprojectbe.request.UpdatePersonalInformationRequest;
import com.example.graduationprojectbe.response.AuthResponse;
import com.example.graduationprojectbe.response.StatusResponse;
import com.example.graduationprojectbe.sercurity.ICurrentUserLmpl;
import com.example.graduationprojectbe.sercurity.JwtUtils;
import com.example.graduationprojectbe.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;

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
