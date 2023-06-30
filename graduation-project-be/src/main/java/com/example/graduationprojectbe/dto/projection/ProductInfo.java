package com.example.graduationprojectbe.dto.projection;

import com.example.graduationprojectbe.constants.Status;
import com.example.graduationprojectbe.entity.Product;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

/**
 * Projection for {@link com.example.graduationprojectbe.entity.Product}
 */

public interface ProductInfo {
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
    class ProductInfoImpl implements ProductInfo {
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
        public LocalDateTime getCreateGuaranteeDate() {
            return product.getCreateGuaranteeDate();
        }

        @Override
        public LocalDateTime getFinishDate() {
            return product.getFinishDate();
        }
    }

    static ProductInfo of(Product product) {
        return new ProductInfoImpl(product);
    }
}
