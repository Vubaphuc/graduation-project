package com.example.graduationprojectbe.dto.projection;

import com.example.graduationprojectbe.entity.Components;
import com.example.graduationprojectbe.entity.Material;
import com.example.graduationprojectbe.entity.OrderMaterial;
import com.example.graduationprojectbe.entity.User;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;


/**
 * Projection for {@link com.example.graduationprojectbe.entity.OrderMaterial}
 */

public interface OrderMaterialProjection {
    Integer getId();
    String getOrderCode();
    int getQuantity();
    boolean getStatus();
    LocalDateTime getCreateDate();
    LocalDateTime getApprovalDate();
    MaterialProjection getMaterial();
    EmployeeInfo getApprover();
    EmployeeInfo getOrderer();

    @RequiredArgsConstructor
    class OrderMaterialProjectionImpl implements OrderMaterialProjection {
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

        @Override
        public MaterialProjection getMaterial() {
            if (orderMaterial.getMaterial() == null) return null;
            return MaterialProjection.of(orderMaterial.getMaterial());
        }

        @Override
        public EmployeeInfo getApprover() {
            if (orderMaterial.getApprover() == null) return null;
            return EmployeeInfo.of(orderMaterial.getApprover());
        }

        @Override
        public EmployeeInfo getOrderer() {
            if (orderMaterial.getOrderer() == null) return null;
            return EmployeeInfo.of(orderMaterial.getOrderer());
        }
    }

    static OrderMaterialProjection of (OrderMaterial orderMaterial) {
        return new OrderMaterialProjectionImpl(orderMaterial);
    }
}
