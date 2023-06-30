package com.example.graduationprojectbe.dto.dto;

public class TotalProductByEngineerDto {
    private String employeeCode;
    private String employeeName;
    private long totalProduct;

    public TotalProductByEngineerDto(String employeeCode, String employeeName, long totalProduct) {
        this.employeeCode = employeeCode;
        this.employeeName = employeeName;
        this.totalProduct = totalProduct;
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

    public long getTotalProduct() {
        return totalProduct;
    }

    public void setTotalProduct(long totalProduct) {
        this.totalProduct = totalProduct;
    }
}
