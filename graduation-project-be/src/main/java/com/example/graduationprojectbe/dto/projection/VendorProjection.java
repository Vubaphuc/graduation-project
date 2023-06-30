package com.example.graduationprojectbe.dto.projection;

import com.example.graduationprojectbe.entity.User;
import com.example.graduationprojectbe.entity.Vendor;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

/**
 * Projection for {@link com.example.graduationprojectbe.entity.Vendor}
 */

public interface VendorProjection {
    Integer getId();
    String getName();
    LocalDateTime getCreateDate();
    LocalDateTime getUpdateDate();
    EmployeeInfo getWarehouseEmployee();

    @RequiredArgsConstructor
    class VendorProjectionImpl implements VendorProjection {
        private final Vendor vendor;

        @Override
        public Integer getId() {
            return vendor.getId();
        }

        @Override
        public String getName() {
            return vendor.getName();
        }

        @Override
        public LocalDateTime getCreateDate() {
            return vendor.getCreateDate();
        }

        @Override
        public LocalDateTime getUpdateDate() {
            return vendor.getUpdateDate();
        }

        @Override
        public EmployeeInfo getWarehouseEmployee() {
            if (vendor.getWarehouseEmployee() == null) return null;
            return EmployeeInfo.of(vendor.getWarehouseEmployee());
        }
    }

    static VendorProjection of(Vendor vendor) {
        return new VendorProjectionImpl(vendor);
    }
}
