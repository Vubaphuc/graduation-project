package com.example.graduationprojectbe.dto.projection;

import com.example.graduationprojectbe.entity.Product;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Projection for {@link com.example.graduationprojectbe.entity.Product}
 */
public interface CustomerSearchInfo {
    Integer getId();
    String getNameModel();
    String getPhoneCompany();
    String getIme();
    String getStatus();
    double getPrice();
    LocalDateTime getInputDate();
    LocalDateTime getOutputDate();
    EmployeeInfo getReceptionists();
    CustomerInfo getCustomer();
    ComponentInfor getComponents();
    EmployeeInfo getProductPayer();
    List<GuaranteeInfo> getGuarantees();

    @RequiredArgsConstructor
    class CustomerSearchInfoImpl implements CustomerSearchInfo {
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
        public String getStatus() {
            return product.getStatus().getMessage();
        }

        @Override
        public double getPrice() {
            return product.getPrice();
        }

        @Override
        public LocalDateTime getInputDate() {
            return product.getInputDate();
        }

        @Override
        public LocalDateTime getOutputDate() {
            return product.getOutputDate();
        }

        @Override
        public EmployeeInfo getReceptionists() {
            if (product.getReceptionists() == null) return null;
            return EmployeeInfo.of(product.getReceptionists());
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
            if (product.getGuarantees().isEmpty()) return new ArrayList<>();
            return product.getGuarantees().stream()
                    .map(GuaranteeInfo::of)
                    .collect(Collectors.toList());
        }
    }

    static CustomerSearchInfo of (Product product) {
        return new CustomerSearchInfoImpl(product);
    }

}
