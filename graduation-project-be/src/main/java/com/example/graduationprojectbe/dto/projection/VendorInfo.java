package com.example.graduationprojectbe.dto.projection;

import com.example.graduationprojectbe.entity.Vendor;
import jakarta.persistence.Column;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

/**
 * Projection for {@link com.example.graduationprojectbe.entity.Vendor}
 */

public interface VendorInfo {
    Integer getId();
    String getName();
    LocalDateTime getCreateDate();
    LocalDateTime getUpdateDate();

    @RequiredArgsConstructor
    class VendorInfoImpl implements VendorInfo {
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
    }

    static VendorInfo of (Vendor vendor) {
        return new VendorInfoImpl(vendor);
    }
}
