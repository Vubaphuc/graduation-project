package com.example.graduationprojectbe.dto.projection;

import com.example.graduationprojectbe.entity.Role;
import lombok.RequiredArgsConstructor;

/**
 * Projection for {@link com.example.graduationprojectbe.entity.Role}
 */
public interface RoleInfo {
    Integer getId();
    String getName();

    @RequiredArgsConstructor
    class RoleImpl implements RoleInfo {
        private final Role role;

        @Override
        public Integer getId() {
            return role.getId();
        }

        @Override
        public String getName() {
            return role.getName();
        }
    }

    static RoleInfo of(Role role) {
        return new RoleImpl(role);
    }
}
