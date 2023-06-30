package com.example.graduationprojectbe.dto.projection;

import com.example.graduationprojectbe.entity.User;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Projection for {@link com.example.graduationprojectbe.entity.User}
 */
public interface EmployeeProjection {
    Integer getId();
    String getEmployeeCode();
    String getEmployeeName();
    String getEmail();
    String getPhoneNumber();
    String getAddress();
    boolean getEnabled();

    List<RoleInfo> getRoles();


    @RequiredArgsConstructor
    class EmployeeImpl implements EmployeeProjection {
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

        @Override
        public String getEmail() {
            return user.getEmail();
        }

        @Override
        public String getPhoneNumber() {
            return user.getPhoneNumber();
        }

        @Override
        public String getAddress() {
            return user.getAddress();
        }

        @Override
        public boolean getEnabled() {
            return user.isEnabled();
        }


        @Override
        public List<RoleInfo> getRoles() {
            return user.getRoles().stream()
                    .map(RoleInfo::of)
                    .collect(Collectors.toList());
        }
    }

    static EmployeeProjection of (User user) {
        return new EmployeeImpl(user);
    }
}
