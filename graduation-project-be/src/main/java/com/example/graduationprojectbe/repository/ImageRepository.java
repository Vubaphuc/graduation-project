package com.example.graduationprojectbe.repository;

import com.example.graduationprojectbe.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Integer> {
}