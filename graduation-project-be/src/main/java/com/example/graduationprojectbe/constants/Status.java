package com.example.graduationprojectbe.constants;

public enum Status {
    WAITING_FOR_REPAIR("Đang chờ sửa chữa"),
    UNDER_REPAIR("Đang được sửa chữa"),
    REPAIRED("Đã sửa chữa xong"),
    WAITING_FOR_RETURN("Đang chờ trả khách"),
    DELIVERED("Đã giao trả khách hàng");

    private String status;

    Status(String status) {
        this.status = status;
    }
    public String getMessage() {
        return status;
    }
}
