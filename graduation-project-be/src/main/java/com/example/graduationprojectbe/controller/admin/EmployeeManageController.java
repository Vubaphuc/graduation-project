package com.example.graduationprojectbe.controller.admin;

import com.example.graduationprojectbe.request.AdminCreateEmployeeRequest;
import com.example.graduationprojectbe.request.AdminUpdateInformationEmployeeRequest;
import com.example.graduationprojectbe.request.AdminUpdatePasswordRequest;
import com.example.graduationprojectbe.service.admin.EmployeeManageService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("admin/api/v1")
public class EmployeeManageController {
    @Autowired
    private EmployeeManageService employeeManageService;

    // lấy tất cả danh sách Nhân Viên
    @GetMapping("employees")
    public ResponseEntity<?> findEmployeesAll(@RequestParam(defaultValue = "1") Integer page,
                                               @RequestParam(defaultValue = "10") Integer pageSize,
                                               @RequestParam(defaultValue = "") String term) {
        return ResponseEntity.ok(employeeManageService.findEmployeesAll(page,pageSize,term));
    }
    // lấy nhân viên theo id
    @GetMapping("employee/{id}")
    public ResponseEntity<?> findEmployeeById(@PathVariable Integer id) {
        return ResponseEntity.ok(employeeManageService.findEmployeeById(id));
    }
    // lấy danh sách roles
    @GetMapping("roles")
    public ResponseEntity<?> findRolesAll() {
        return ResponseEntity.ok(employeeManageService.findRolesAll());
    }
    // tạo nhân viên mới
    @PostMapping("create/employee")
    public ResponseEntity<?> createEmployee (@Valid @RequestBody AdminCreateEmployeeRequest request) {
        return ResponseEntity.ok(employeeManageService.createEmployee(request));
    }
    // cập nhật thông tin nhân viên theo id
    @PutMapping("employee/{id}")
    public ResponseEntity<?> updateInformationEmployeeById(@RequestBody AdminUpdateInformationEmployeeRequest request, @PathVariable Integer id) {
        return ResponseEntity.ok(employeeManageService.updateInformationEmployeeById(request, id));
    }
    // cập nhật mật khẩu tài khoản nhân viên theo id
    @PutMapping("employee/password/{id}")
    public ResponseEntity<?> updatePasswordAccEmployeeById(@RequestBody AdminUpdatePasswordRequest request, @PathVariable Integer id) {
        return ResponseEntity.ok(employeeManageService.updatePasswordAccEmployeeById(request, id));
    }
    // xóa nhân viên đồng thời  khóa tài khoản nhân viên không sử dụng nữa
    @DeleteMapping("employee/{id}")
    public ResponseEntity<?> deleteEmployeeById (@PathVariable Integer id) {
        return ResponseEntity.ok(employeeManageService.deleteEmployeeById(id));
    }
}
