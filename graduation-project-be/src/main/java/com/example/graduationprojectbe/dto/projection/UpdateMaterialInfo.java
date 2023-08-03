package com.example.graduationprojectbe.dto.projection;

import com.example.graduationprojectbe.entity.UpdateMaterial;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

/**
 * Projection for {@link com.example.graduationprojectbe.entity.UpdateMaterial}
 */
public interface UpdateMaterialInfo {
    Integer getId();

    Integer getQuantity();

    LocalDateTime getUpdateDate();

    EmployeeInfo getEmployeeUpdate();

    MaterialDetail getMaterial();

    @RequiredArgsConstructor
    class UpdateMaterialInfoImpl implements UpdateMaterialInfo {
        private final UpdateMaterial updateMaterial;

        @Override
        public Integer getId() {
            return updateMaterial.getId();
        }

        @Override
        public Integer getQuantity() {
            return updateMaterial.getQuantity();
        }

        @Override
        public LocalDateTime getUpdateDate() {
            return updateMaterial.getUpdateDate();
        }

        @Override
        public EmployeeInfo getEmployeeUpdate() {
            return EmployeeInfo.of(updateMaterial.getEmployeeUpdate());
        }

        @Override
        public MaterialDetail getMaterial() {
            return MaterialDetail.of(updateMaterial.getMaterial());
        }
    }

    static UpdateMaterialInfo of (UpdateMaterial updateMaterial) {
        return new UpdateMaterialInfoImpl(updateMaterial);
    }
}