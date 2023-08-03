package com.example.graduationprojectbe.controller.auth;

import com.example.graduationprojectbe.entity.Image;
import com.example.graduationprojectbe.request.ChangePasswordRequest;
import com.example.graduationprojectbe.request.ForgotPasswordRequest;
import com.example.graduationprojectbe.request.LoginRequest;
import com.example.graduationprojectbe.request.UpdatePersonalInformationRequest;
import com.example.graduationprojectbe.service.auth.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("public/api")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("login")
    public ResponseEntity<?> login (@Valid @RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }
}
