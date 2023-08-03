package com.example.graduationprojectbe.dto.projection;

import com.example.graduationprojectbe.entity.Receipt;
import com.example.graduationprojectbe.entity.Role;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

/**
 * Projection for {@link com.example.graduationprojectbe.entity.Receipt}
 */
public interface ReceiptInfo {
    Integer getId();
    Integer getQuantity();
    LocalDateTime getCreateDate();
    LocalDateTime getPayDate();
    boolean isStatus();
    ProductProjection getProduct();
    EmployeeInfo getEmployeeCreate();
    @RequiredArgsConstructor
    class ReceiptInfoImpl implements ReceiptInfo {
        private final Receipt receipt;


        @Override
        public Integer getId() {
            return receipt.getId();
        }

        @Override
        public Integer getQuantity() {
            return receipt.getQuantity();
        }

        @Override
        public LocalDateTime getCreateDate() {
            return receipt.getCreateDate();
        }

        @Override
        public LocalDateTime getPayDate() {
            return receipt.getPayDate();
        }

        @Override
        public boolean isStatus() {
            return receipt.isStatus();
        }

        @Override
        public ProductProjection getProduct() {
            return ProductProjection.of(receipt.getProduct());
        }

        @Override
        public EmployeeInfo getEmployeeCreate() {
            return EmployeeInfo.of(receipt.getEmployeeCreate());
        }
    }

    static ReceiptInfo of(Receipt receipt) {
        return new ReceiptInfoImpl(receipt);
    }
}