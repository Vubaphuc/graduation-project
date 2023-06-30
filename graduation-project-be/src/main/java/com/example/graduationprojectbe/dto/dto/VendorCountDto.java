package com.example.graduationprojectbe.dto.dto;

import lombok.*;


public class VendorCountDto {
    private Integer id;
    private String name;
    private Long quantityMaterial;

    public VendorCountDto(Integer id, String name, Long quantityMaterial) {
        this.id = id;
        this.name = name;
        this.quantityMaterial = quantityMaterial;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getQuantityMaterial() {
        return quantityMaterial;
    }

    public void setQuantityMaterial(Long quantityMaterial) {
        this.quantityMaterial = quantityMaterial;
    }
}
