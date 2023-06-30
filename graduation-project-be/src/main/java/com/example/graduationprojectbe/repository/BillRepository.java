package com.example.graduationprojectbe.repository;

import com.example.graduationprojectbe.entity.Bill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillRepository extends JpaRepository<Bill, Integer> {
}