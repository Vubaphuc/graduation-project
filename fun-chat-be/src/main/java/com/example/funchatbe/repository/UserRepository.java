package com.example.funchatbe.repository;

import com.example.funchatbe.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findUsersByEmail(String email);
}