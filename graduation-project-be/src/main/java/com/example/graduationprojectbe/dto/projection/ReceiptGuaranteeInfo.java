package com.example.graduationprojectbe.dto.projection;

import com.example.graduationprojectbe.entity.Receipt;
import com.example.graduationprojectbe.entity.ReceiptGuarantee;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

/**
 * Projection for {@link com.example.graduationprojectbe.entity.ReceiptGuarantee}
 */
public interface ReceiptGuaranteeInfo {
    Integer getId();

    Integer getQuantity();

    LocalDateTime getCreateDate();

    LocalDateTime getPayDate();

    boolean isStatus();

    EmployeeInfo getEmployeeCreate();

    ProductGuaranteeProjection getProductGuarantee();

    @RequiredArgsConstructor
    class ReceiptGuaranteeInfoImpl implements ReceiptGuaranteeInfo {
        private final ReceiptGuarantee receiptGuarantee;


        @Override
        public Integer getId() {
            return receiptGuarantee.getId();
        }

        @Override
        public Integer getQuantity() {
            return receiptGuarantee.getQuantity();
        }

        @Override
        public LocalDateTime getCreateDate() {
            return receiptGuarantee.getCreateDate();
        }

        @Override
        public LocalDateTime getPayDate() {
            return receiptGuarantee.getPayDate();
        }

        @Override
        public boolean isStatus() {
            return receiptGuarantee.isStatus();
        }

        @Override
        public EmployeeInfo getEmployeeCreate() {
            return EmployeeInfo.of(receiptGuarantee.getEmployeeCreate());
        }

        @Override
        public ProductGuaranteeProjection getProductGuarantee() {
            return ProductGuaranteeProjection.of(receiptGuarantee.getProductGuarantee());
        }
    }

    static ReceiptGuaranteeInfo of(ReceiptGuarantee receiptGuarantee) {
        return new ReceiptGuaranteeInfoImpl(receiptGuarantee);
    }
}