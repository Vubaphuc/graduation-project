package com.example.graduationprojectbe.repository;

import com.example.graduationprojectbe.entity.Guarantee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GuaranteeRepository extends JpaRepository<Guarantee, Integer> {
}