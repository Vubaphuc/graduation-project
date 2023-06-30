package com.example.graduationprojectbe.service;


import com.example.graduationprojectbe.exception.NotFoundException;
import com.example.graduationprojectbe.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserDetails userDetails = userRepository.findUsersByEmail(email).orElseThrow(() -> {
            throw new NotFoundException("Not Found User");
        });

        if (!userDetails.isEnabled()){
            throw new DisabledException("User account is disabled");
        }

        return userDetails;
    }
}
