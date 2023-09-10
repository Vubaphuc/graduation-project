package com.example.funchatbe.service;

import com.example.funchatbe.exception.NotFoundException;
import com.example.funchatbe.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserDetails userDetails = userRepository.findUsersByEmail(email).orElseThrow(() -> new NotFoundException("Not Found User"));

        if (!userDetails.isEnabled()) {
            throw new DisabledException("User account is disabled");
        }

        return userDetails;
    }
}
