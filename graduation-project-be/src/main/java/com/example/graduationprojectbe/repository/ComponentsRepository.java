package com.example.graduationprojectbe.repository;

import com.example.graduationprojectbe.dto.projection.ComponentInfor;
import com.example.graduationprojectbe.dto.projection.ComponentProjection;
import com.example.graduationprojectbe.entity.Components;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ComponentsRepository extends JpaRepository<Components, Integer> {
    Optional<Components> findByName(String name);

    //#####################################################################################################################
    //############################ Nhân Viên Kho ############################################################################

    // lấy ra danh sách Components có phân trang - 1
    @Query("select cp from Components cp where cp.delete = true ")
    Page<ComponentProjection> getListComponentPhone(Pageable pageable);

    // lấy ra linh kiện theo id - 2
    @Query("select cp from Components cp where cp.id = ?1 and cp.delete = true ")
    Optional<ComponentProjection> getComponentsById(Integer id);
}