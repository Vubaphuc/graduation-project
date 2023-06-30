package com.example.graduationprojectbe.dto.projection;

import com.example.graduationprojectbe.constants.Status;
import com.example.graduationprojectbe.entity.Product;
import lombok.RequiredArgsConstructor;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Projection for {@link com.example.graduationprojectbe.entity.Product}
 */

public interface ProductProjection {
    Integer getId();
    String getNameModel();
    String getPhoneCompany();
    String getIme();
    String getDefectName();
    Status getStatus();
    double getPrice();
    boolean getRepair();
    LocalDateTime getInputDate();
    LocalDateTime getCreateGuaranteeDate();
    LocalDateTime getTransferDate();
    String getLocation();
    String getNote();
    LocalDateTime getOutputDate();
    LocalDateTime getFinishDate();
    EmployeeInfo getReceptionists();
    EmployeeInfo getEngineer();
    CustomerInfo getCustomer();
    ComponentInfor getComponents();
    EmployeeInfo getProductPayer();
    List<GuaranteeInfo> getGuarantees();

    @RequiredArgsConstructor
    class ProductProjectionImpl implements ProductProjection {
        private final Product product;

        @Override
        public Integer getId() {
            return product.getId();
        }

        @Override
        public String getNameModel() {
            return product.getNameModel();
        }

        @Override
        public String getPhoneCompany() {
            return product.getPhoneCompany();
        }

        @Override
        public String getIme() {
            return product.getIme();
        }

        @Override
        public String getDefectName() {
            return product.getDefectName();
        }

        @Override
        public Status getStatus() {
            return product.getStatus();
        }

        @Override
        public double getPrice() {
            return product.getPrice();
        }

        @Override
        public boolean getRepair() {
            return product.isRepair();
        }
        @Override
        public LocalDateTime getInputDate() {
            return product.getInputDate();
        }

        @Override
        public LocalDateTime getCreateGuaranteeDate() {
            return product.getCreateGuaranteeDate();
        }

        @Override
        public LocalDateTime getTransferDate() {
            return product.getTransferDate();
        }

        @Override
        public String getLocation() {
            return product.getLocation();
        }

        @Override
        public String getNote() {
            return product.getNote();
        }

        @Override
        public LocalDateTime getOutputDate() {
            return product.getOutputDate();
        }

        @Override
        public LocalDateTime getFinishDate() {
            return product.getFinishDate();
        }

        @Override
        public EmployeeInfo getReceptionists() {
            if (product.getReceptionists() == null) return null;
            return EmployeeInfo.of(product.getReceptionists());
        }

        @Override
        public EmployeeInfo getEngineer() {
            if (product.getEngineer() == null) return null;
            return EmployeeInfo.of(product.getEngineer());
        }

        @Override
        public CustomerInfo getCustomer() {
            if (product.getCustomer() == null) return null;
            return CustomerInfo.of(product.getCustomer());
        }

        @Override
        public ComponentInfor getComponents() {
            if (product.getComponents() == null) return null;
            return ComponentInfor.of(product.getComponents());
        }

        @Override
        public EmployeeInfo getProductPayer() {
            if (product.getProductPayer() == null) return null;
            return EmployeeInfo.of(product.getProductPayer());
        }

        @Override
        public List<GuaranteeInfo> getGuarantees() {
            if (product.getGuarantees().isEmpty()) return null;
            return product.getGuarantees().stream()
                    .map(GuaranteeInfo::of)
                    .collect(Collectors.toList());
        }
    }

    static ProductProjection of (Product product) {
        return new ProductProjectionImpl(product);
    }
}
