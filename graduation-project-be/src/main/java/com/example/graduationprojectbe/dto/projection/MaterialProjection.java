package com.example.graduationprojectbe.dto.projection;

import com.example.graduationprojectbe.entity.Material;
import com.example.graduationprojectbe.entity.Vendor;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

/**
 * Projection for {@link com.example.graduationprojectbe.entity.Material}
 */

public interface MaterialProjection {
    Integer getId();
    String getCode();
    String getNameModel();
    int getImportQuantity();
    int getExportQuantity();
    int getRemainingQuantity();
    LocalDateTime getCreateDate();
    LocalDateTime getUpdateDate();
    double getPrice();
    VendorInfo getVendor();
    ComponentInfor getComponents();
    EmployeeInfo getWarehouseEmployee();
    @RequiredArgsConstructor
    class MaterialProjectionImpl implements MaterialProjection {
        private final Material material;

        @Override
        public Integer getId() {
            return material.getId();
        }

        @Override
        public String getCode() {
            return material.getCode();
        }

        @Override
        public String getNameModel() {
            return material.getNameModel();
        }

        @Override
        public int getImportQuantity() {
            return material.getImportQuantity();
        }

        @Override
        public int getExportQuantity() {
            return material.getExportQuantity();
        }

        @Override
        public int getRemainingQuantity() {
            return material.getRemainingQuantity();
        }

        @Override
        public LocalDateTime getCreateDate() {
            return material.getCreateDate();
        }

        @Override
        public LocalDateTime getUpdateDate() {
            return material.getUpdateDate();
        }

        @Override
        public double getPrice() {
            return material.getPrice();
        }

        @Override
        public VendorInfo getVendor() {
            if (material.getVendor() == null) return null;
            return VendorInfo.of(material.getVendor());
        }

        @Override
        public ComponentInfor getComponents() {
            if (material.getComponents() == null) return null;
            return ComponentInfor.of(material.getComponents());
        }

        @Override
        public EmployeeInfo getWarehouseEmployee() {
            if (material.getWarehouseEmployee() == null) return null;
            return EmployeeInfo.of(material.getWarehouseEmployee());
        }
    }

    static MaterialProjection of (Material material) {
        return new MaterialProjectionImpl(material);
    }
}
