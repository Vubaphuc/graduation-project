package com.example.graduationprojectbe.dto.projection;

import com.example.graduationprojectbe.entity.ProductGuarantee;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

public interface VisitorSearchInfo {
    Integer getId();
    String getNameModel();
    String getPhoneCompany();
    String getIme();

    String getStatus();
    double getPrice();
    LocalDateTime getInputDate();
    LocalDateTime getOutputDate();

    @RequiredArgsConstructor
    class VisitorSearchInfoImpl implements VisitorSearchInfo {
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
        public String getStatus() {
            return productGuarantee.getStatus().getMessage();
        }

        @Override
        public double getPrice() {
            return productGuarantee.getPrice();
        }

        @Override
        public LocalDateTime getInputDate() {
            return productGuarantee.getInputDate();
        }

        @Override
        public LocalDateTime getOutputDate() {
            return productGuarantee.getOutputDate();
        }
    }

    static VisitorSearchInfo of (ProductGuarantee productGuarantee) {
        return new VisitorSearchInfoImpl(productGuarantee);
    }
}
