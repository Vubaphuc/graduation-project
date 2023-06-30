package com.example.graduationprojectbe.repository;

import com.example.graduationprojectbe.entity.UsedCodes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsedCodesRepository extends JpaRepository<UsedCodes, Integer> {

    boolean existsByCode(String code);
}