package com.example.graduationprojectbe.dto.dto;


public class CustomerDto {
    private Integer id;
    private String fullName;
    private String phone;
    private String email;
    private String address;
    private Long sumProduct;

    public CustomerDto(Integer id, String fullName, String phone, String email, String address, Long sumProduct) {
        this.id = id;
        this.fullName = fullName;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.sumProduct = sumProduct;
    }

    // Getters and Setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getSumProduct() {
        return sumProduct;
    }

    public void setSumProduct(Long sumProduct) {
        this.sumProduct = sumProduct;
    }
}
