package com.example.graduationprojectbe.repository;

import com.example.graduationprojectbe.dto.projection.UpdateMaterialInfo;
import com.example.graduationprojectbe.entity.UpdateMaterial;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UpdateMaterialRepository extends JpaRepository<UpdateMaterial, Integer> {
    @Query("select um from UpdateMaterial um")
    Page<UpdateMaterialInfo> findMaterialUpdate(Pageable pageable);
}