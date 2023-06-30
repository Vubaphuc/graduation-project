package com.example.graduationprojectbe.repository;

import com.example.graduationprojectbe.dto.projection.RoleInfo;
import com.example.graduationprojectbe.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByName(String name);

    //#####################################################################################################################
    //############################  ADMIN  ############################################################################
    @Query("select rl from Role rl where rl.name <> 'ADMIN' ")
    List<RoleInfo> findRolesAll();
}