package com.example.graduationprojectbe.dto.projection;

import com.example.graduationprojectbe.entity.User;
import lombok.RequiredArgsConstructor;

/**
 * Projection for {@link com.example.graduationprojectbe.entity.User}
 */
public interface EmployeeInfo {
    Integer getId();
    String getEmployeeCode();
    String getEmployeeName();

    @RequiredArgsConstructor
    class EmployeeImpl implements EmployeeInfo {
        private final User user;

        @Override
        public Integer getId() {
            return user.getId();
        }

        @Override
        public String getEmployeeCode() {
            return user.getEmployeeCode();
        }

        @Override
        public String getEmployeeName() {
            return user.getEmployeeName();
        }
    }

    static EmployeeInfo of(User user) {
        return new EmployeeImpl(user);
    }
}
