package com.example.graduationprojectbe.dto.projection;

import com.example.graduationprojectbe.entity.Bill;
import com.example.graduationprojectbe.entity.Product;
import com.example.graduationprojectbe.entity.User;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

/**
 * Projection for {@link com.example.graduationprojectbe.entity.Bill}
 */
public interface BillProjection {
    Integer getId();
    LocalDateTime getInvoiceCreationDate();
    EmployeeInfo getInvoiceCreator();

    ProductInfo getProduct();

    @RequiredArgsConstructor
    class BillProjectionImpl implements BillProjection {
        private final Bill bill;

        @Override
        public Integer getId() {
            return bill.getId();
        }

        @Override
        public LocalDateTime getInvoiceCreationDate() {
            return bill.getInvoiceCreationDate();
        }

        @Override
        public EmployeeInfo getInvoiceCreator() {
            if (bill.getInvoiceCreator() == null) return null;
            return EmployeeInfo.of(bill.getInvoiceCreator());
        }

        @Override
        public ProductInfo getProduct() {
            if (bill.getProduct() == null) return null;
            return ProductInfo.of(bill.getProduct());
        }
    }

    static BillProjection of(Bill bill) {
        return new BillProjection.BillProjectionImpl(bill);
    }
}
