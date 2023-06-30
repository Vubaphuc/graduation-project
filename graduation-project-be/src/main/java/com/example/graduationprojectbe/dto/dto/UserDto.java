package com.example.graduationprojectbe.dto.dto;


import com.example.graduationprojectbe.entity.Role;
import lombok.*;

import java.util.List;

public class UserDto {
    private Integer id;
    private String employeeCode;
    private String employeeName;
    private String email;
    private String phone;
    private String address;
    private List<Role> roles;

    public UserDto(Integer id, String employeeCode, String employeeName, String email, String phone, String address, List<Role> roles) {
        this.id = id;
        this.employeeCode = employeeCode;
        this.employeeName = employeeName;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.roles = roles;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmployeeCode() {
        return employeeCode;
    }

    public void setEmployeeCode(String employeeCode) {
        this.employeeCode = employeeCode;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
