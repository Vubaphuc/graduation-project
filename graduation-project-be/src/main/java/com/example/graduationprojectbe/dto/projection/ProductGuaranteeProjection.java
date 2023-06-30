package com.example.graduationprojectbe.dto.projection;

import com.example.graduationprojectbe.constants.Status;
import com.example.graduationprojectbe.entity.*;
import jakarta.persistence.*;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Projection for {@link com.example.graduationprojectbe.entity.ProductGuarantee}
 */

public interface ProductGuaranteeProjection {
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
    EmployeeInfo getWarranty();
    EmployeeInfo getEngineer();
    CustomerInfo getCustomer();
    ComponentInfor getComponents();
    EmployeeInfo getProductPayer();

    @RequiredArgsConstructor
    class ProductGuaranteeProjectionImpl implements ProductGuaranteeProjection {
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

        @Override
        public EmployeeInfo getWarranty() {
            if (productGuarantee.getWarranty() == null) return null;
            return EmployeeInfo.of(productGuarantee.getWarranty());
        }

        @Override
        public EmployeeInfo getEngineer() {
            if (productGuarantee.getEngineer() == null) return null;
            return EmployeeInfo.of(productGuarantee.getEngineer());
        }

        @Override
        public CustomerInfo getCustomer() {
            if (productGuarantee.getCustomer() == null) return null;
            return CustomerInfo.of(productGuarantee.getCustomer());
        }

        @Override
        public ComponentInfor getComponents() {
            if (productGuarantee.getComponents() == null) return null;
            return ComponentInfor.of(productGuarantee.getComponents());
        }

        @Override
        public EmployeeInfo getProductPayer() {
            if (productGuarantee.getProductPayer() == null) return null;
            return EmployeeInfo.of(productGuarantee.getProductPayer());
        }

    }

    static ProductGuaranteeProjection of (ProductGuarantee productGuarantee) {
        return new ProductGuaranteeProjectionImpl(productGuarantee);
    }
}
