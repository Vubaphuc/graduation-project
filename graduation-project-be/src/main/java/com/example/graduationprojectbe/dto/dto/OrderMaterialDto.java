package com.example.graduationprojectbe.dto.dto;

public class OrderMaterialDto {
    private String materialCode;
    private String componentName;
    private long totalQuantityExport;

    public OrderMaterialDto(String materialCode, String componentName, long totalQuantityExport) {
        this.materialCode = materialCode;
        this.componentName = componentName;
        this.totalQuantityExport = totalQuantityExport;
    }

    public String getMaterialCode() {
        return materialCode;
    }

    public void setMaterialCode(String materialCode) {
        this.materialCode = materialCode;
    }

    public String getComponentName() {
        return componentName;
    }

    public void setComponentName(String componentName) {
        this.componentName = componentName;
    }

    public long getTotalQuantityExport() {
        return totalQuantityExport;
    }

    public void setTotalQuantityExport(long totalQuantityExport) {
        this.totalQuantityExport = totalQuantityExport;
    }
}
