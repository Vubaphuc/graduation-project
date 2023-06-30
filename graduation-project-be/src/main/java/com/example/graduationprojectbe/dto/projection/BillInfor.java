package com.example.graduationprojectbe.dto.projection;

import com.example.graduationprojectbe.entity.Bill;
import jakarta.persistence.Column;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;


/**
 * Projection for {@link com.example.graduationprojectbe.entity.Bill}
 */
public interface BillInfor {
    Integer getId();
    LocalDateTime getInvoiceCreationDate();

    @RequiredArgsConstructor
    class BillInforImpl implements BillInfor {
        private final Bill bill;

        @Override
        public Integer getId() {
            return bill.getId();
        }

        @Override
        public LocalDateTime getInvoiceCreationDate() {
            return bill.getInvoiceCreationDate();
        }
    }

    static BillInfor of(Bill bill) {
        return new BillInforImpl(bill);
    }
}
