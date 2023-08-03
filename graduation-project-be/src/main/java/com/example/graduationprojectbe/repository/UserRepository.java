package com.example.graduationprojectbe.repository;

import com.example.graduationprojectbe.dto.projection.EmployeeInfo;
import com.example.graduationprojectbe.dto.projection.EmployeeProjection;
import com.example.graduationprojectbe.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    // lấy user theo email
    Optional<User> findUsersByEmail(String email);
    Optional<User> findByEmployeeCode(String employeeCode);


    // nhân viên chung - lấy danh sách nhân viên sủa chữa
    @Query("select u from User u join u.roles rl where rl.name = 'NHANVIENSUACHUA' ")
    List<EmployeeInfo> findEngineerAll();

    //   nhân viên chung - lấy danh sách nhân viên lễ tân
    @Query("select u from User u join u.roles rl where rl.name = 'NHANVIENLETAN' ")
    List<EmployeeInfo> findReceptionistAll();

    // nhân viên chung - lấy danh sách nhân viên kho
    @Query("select u from User u join u.roles rl where rl.name = 'NHANVIENKHO' ")
    List<EmployeeInfo> findWarehouseEmployeeAll();

    // nhân viên chung - lấy danh sách nhân viên bảo hành và lễ tân
    @Query("select u from User u join u.roles rl where rl.name = 'NHANVIENLETAN' or rl.name = 'NHANVIENBAOHANH' ")
    List<EmployeeInfo> findReceptionistAndWarrantyEmployeeAll();

    //#####################################################################################################################
    //############################  ADMIN  ############################################################################
    @Query("select u from User u where u.id = ?1 ")
    Optional<EmployeeProjection> findEmployeeById(Integer id);

    @Query("select u from User u join u.roles rl where u.email like %?1% and rl.name <> 'ADMIN' ")
    Page<EmployeeProjection> findEmployeesAll(Pageable pageable, String term);

    @Query("select u from User u join u.roles rl where rl.name = 'NHANVIENSUACHUA' ")
    List<User> findEmployeeEngineerAll();

    @Query("select u from User u where u.enabled = true ")
    List<EmployeeInfo> getAllUsers();

    @Query("select u.email from User u")
    List<String> findAllEmails();
}