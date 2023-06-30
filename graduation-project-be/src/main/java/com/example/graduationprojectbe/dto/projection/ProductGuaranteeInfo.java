package com.example.graduationprojectbe.dto.projection;

import com.example.graduationprojectbe.constants.Status;
import com.example.graduationprojectbe.entity.ProductGuarantee;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

/**
 * Projection for {@link com.example.graduationprojectbe.entity.ProductGuarantee}
 */

public interface ProductGuaranteeInfo {
    Integer getId();
    String getNameModel();
    String getPhoneCompany();
    String getIme();
    String getDefectName();
    Status getStatus();
    double getPrice();
    boolean getRepair();
    LocalDateTime getInputDate();
    LocalDateTime getTransferDate();
    String getLocation();
    String getNote();
    LocalDateTime getOutputDate();
    LocalDateTime getCreateGuaranteeDate();
    LocalDateTime getFinishDate();

    @RequiredArgsConstructor
    class ProductGuaranteeInfoImpl implements ProductGuaranteeInfo {
        private final ProductGuarantee productGuarantee;


        @Override
        public Integer getId() {
            return productGuarantee.getId();
        }

        @Override
        public String getNameModel() {
            return productGuarantee.getNameModel();
        }

        @Override
        public String getPhoneCompany() {
            return productGuarantee.getPhoneCompany();
        }

        @Override
        public String getIme() {
            return productGuarantee.getIme();
        }

        @Override
        public String getDefectName() {
            return productGuarantee.getDefectName();
        }

        @Override
        public Status getStatus() {
            return productGuarantee.getStatus();
        }

        @Override
        public double getPrice() {
            return productGuarantee.getPrice();
        }

        @Override
        public boolean getRepair() {
            return productGuarantee.isRepair();
        }

        @Override
        public LocalDateTime getInputDate() {
            return productGuarantee.getInputDate();
        }

        @Override
        public LocalDateTime getTransferDate() {
            return productGuarantee.getTransferDate();
        }

        @Override
        public String getLocation() {
            return productGuarantee.getLocation();
        }

        @Override
        public String getNote() {
            return productGuarantee.getNote();
        }

        @Override
        public LocalDateTime getOutputDate() {
            return productGuarantee.getOutputDate();
        }

        @Override
        public LocalDateTime getCreateGuaranteeDate() {
            return productGuarantee.getCreateGuaranteeDate();
        }

        @Override
        public LocalDateTime getFinishDate() {
            return productGuarantee.getFinishDate();
        }
    }

    static ProductGuaranteeInfo of (ProductGuarantee productGuarantee) {
        return new ProductGuaranteeInfoImpl(productGuarantee);
    }
}
