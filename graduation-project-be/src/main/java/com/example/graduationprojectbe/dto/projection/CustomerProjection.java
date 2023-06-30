package com.example.graduationprojectbe.dto.projection;

import com.example.graduationprojectbe.entity.Customer;
import lombok.RequiredArgsConstructor;

/**
 * Projection for {@link com.example.graduationprojectbe.entity.Customer}
 */

public interface CustomerProjection {
    Integer getId();
    String getFullName();
    String getPhoneNumber();
    String getEmail();
    String getAddress();
    EmployeeInfo getReceptionists();

    @RequiredArgsConstructor
    class CustomerProjectionImpl implements CustomerProjection {
        private final Customer customer;

        @Override
        public Integer getId() {
            return customer.getId();
        }

        @Override
        public String getFullName() {
            return customer.getFullName();
        }

        @Override
        public String getPhoneNumber() {
            return customer.getPhoneNumber();
        }

        @Override
        public String getEmail() {
            return customer.getEmail();
        }

        @Override
        public String getAddress() {
            return customer.getAddress();
        }

        @Override
        public EmployeeInfo getReceptionists() {
            if (customer.getReceptionists() == null) return null;
            return EmployeeInfo.of(customer.getReceptionists());
        }
    }

    static CustomerProjection of(Customer customer) {
        return new CustomerProjectionImpl(customer);
    }
}
