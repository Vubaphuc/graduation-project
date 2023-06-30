package com.example.graduationprojectbe.dto.projection;

import com.example.graduationprojectbe.entity.Customer;
import lombok.RequiredArgsConstructor;


/**
 * Projection for {@link com.example.graduationprojectbe.entity.Customer}
 */

public interface CustomerInfo {
    Integer getId();
    String getFullName();
    String getPhoneNumber();
    String getEmail();
    String getAddress();

    @RequiredArgsConstructor
    class CustomerInfoImpl implements CustomerInfo {
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
    }

    static CustomerInfo of (Customer customer) {
        return new CustomerInfoImpl(customer);
    }
}
