package com.example.graduationprojectbe.dto.projection;

import com.example.graduationprojectbe.entity.Material;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

/**
 * Projection for {@link com.example.graduationprojectbe.entity.Material}
 */
public interface MaterialDetail {
    Integer getId();

    String getCode();

    String getNameModel();

    @RequiredArgsConstructor
    class MaterialDetailImpl implements MaterialDetail {
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

    }
    static MaterialDetail of (Material material) {
        return new MaterialDetail.MaterialDetailImpl(material);
    }
}