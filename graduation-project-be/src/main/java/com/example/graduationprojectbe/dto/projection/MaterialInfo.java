package com.example.graduationprojectbe.dto.projection;

import com.example.graduationprojectbe.entity.Material;
import lombok.RequiredArgsConstructor;
import java.time.LocalDateTime;
/**
 * Projection for {@link com.example.graduationprojectbe.entity.Material}
 */


public interface MaterialInfo {
    Integer getId();
    String getCode();
    String getNameModel();
    int getImportQuantity();
    int getExportQuantity();
    int getRemainingQuantity();
    LocalDateTime getCreateDate();
    LocalDateTime getUpdateDate();
    double getPrice();

    @RequiredArgsConstructor
    class MaterialInfoImpl implements MaterialInfo {
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
    }

    static MaterialInfo of (Material material) {
        return new MaterialInfoImpl(material);
    }
}
