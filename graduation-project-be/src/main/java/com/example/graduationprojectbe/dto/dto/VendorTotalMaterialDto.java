package com.example.graduationprojectbe.dto.dto;

import lombok.*;

public class VendorTotalMaterialDto {
    private Integer id;
    private String name;
    private long totalMaterial;

    public VendorTotalMaterialDto(Integer id, String name, long totalMaterial) {
        this.id = id;
        this.name = name;
        this.totalMaterial = totalMaterial;
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

    public long getTotalMaterial() {
        return totalMaterial;
    }

    public void setTotalMaterial(long totalMaterial) {
        this.totalMaterial = totalMaterial;
    }
}
