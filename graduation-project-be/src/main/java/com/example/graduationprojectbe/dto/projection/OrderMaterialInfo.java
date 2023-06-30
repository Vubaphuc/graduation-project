package com.example.graduationprojectbe.dto.projection;

import com.example.graduationprojectbe.entity.OrderMaterial;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

/**
 * Projection for {@link com.example.graduationprojectbe.entity.OrderMaterial}
 */

public interface OrderMaterialInfo {
    Integer getId();
    String getOrderCode();
    int getQuantity();
    boolean getStatus();
    LocalDateTime getCreateDate();
    LocalDateTime getApprovalDate();

    @RequiredArgsConstructor
    class OrderMaterialInfoImpl implements OrderMaterialInfo {
        private final OrderMaterial orderMaterial;

        @Override
        public Integer getId() {
            return orderMaterial.getId();
        }

        @Override
        public String getOrderCode() {
            return orderMaterial.getOrderCode();
        }

        @Override
        public int getQuantity() {
            return orderMaterial.getQuantity();
        }

        @Override
        public boolean getStatus() {
            return orderMaterial.isStatus();
        }

        @Override
        public LocalDateTime getCreateDate() {
            return orderMaterial.getCreateDate();
        }

        @Override
        public LocalDateTime getApprovalDate() {
            return orderMaterial.getApprovalDate();
        }
    }

    static OrderMaterialInfo of (OrderMaterial orderMaterial) {
        return new OrderMaterialInfoImpl(orderMaterial);
    }
}
