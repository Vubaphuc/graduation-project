package com.example.graduationprojectbe.dto.projection;

import com.example.graduationprojectbe.entity.Components;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

/**
 * Projection for {@link com.example.graduationprojectbe.entity.Components}
 */

public interface ComponentInfor {
    Integer getId();
    String getName();
    Integer getWarrantyPeriod();
    LocalDateTime getCreateDate();

    @RequiredArgsConstructor
    class ComponentInforImpl implements ComponentInfor {
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
    }

    static ComponentInfor of (Components components) {
        return new ComponentInforImpl(components);
    }
}
