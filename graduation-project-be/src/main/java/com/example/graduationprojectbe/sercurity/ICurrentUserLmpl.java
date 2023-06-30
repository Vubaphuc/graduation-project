package com.example.graduationprojectbe.sercurity;


import com.example.graduationprojectbe.exception.NotFoundException;
import com.example.graduationprojectbe.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import com.example.graduationprojectbe.entity.User;

@Component
@RequiredArgsConstructor
public class ICurrentUserLmpl implements ICurrentUser{

    private final UserRepository userRepository;
    @Override
    public User getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return userRepository.findUsersByEmail(authentication.getName()).orElseThrow(() -> {
            throw new NotFoundException("Not found user");
        });
    }
}
