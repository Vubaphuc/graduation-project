package com.example.graduationprojectbe.dto.projection;

import com.example.graduationprojectbe.entity.Components;
import com.example.graduationprojectbe.entity.User;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

/**
 * Projection for {@link com.example.graduationprojectbe.entity.Components}
 */

public interface ComponentProjection {
    Integer getId();
    String getName();
    Integer getWarrantyPeriod();
    LocalDateTime getCreateDate();
    EmployeeInfo getWarehouseEmployee();

    @RequiredArgsConstructor
    class ComponentProjectionImpl implements ComponentProjection {
        private final Components components;

        @Override
        public Integer getId() {
            return components.getId();
        }

        @Override
        public String getName() {
            return components.getName();
        }

        @Override
        public Integer getWarrantyPeriod() {
            return components.getWarrantyPeriod();
        }

        @Override
        public LocalDateTime getCreateDate() {
            return components.getCreateDate();
        }

        @Override
        public EmployeeInfo getWarehouseEmployee() {
            if (components.getWarehouseEmployee() == null) return null;
            return EmployeeInfo.of(components.getWarehouseEmployee());
        }
    }

    static ComponentProjection of (Components components) {
        return new ComponentProjectionImpl(components);
    }
}
