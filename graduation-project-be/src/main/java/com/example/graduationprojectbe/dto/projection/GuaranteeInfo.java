package com.example.graduationprojectbe.dto.projection;

import com.example.graduationprojectbe.entity.Guarantee;
import lombok.RequiredArgsConstructor;
import java.time.LocalDateTime;

/**
 * Projection for {@link com.example.graduationprojectbe.entity.Guarantee}
 */
public interface GuaranteeInfo {
    Integer getId();
    String getGuaranteeCode();
    LocalDateTime getActivationDate();
    LocalDateTime getExpirationDate();
    boolean getStatus();

    @RequiredArgsConstructor
    class GuaranteeInfoImpl implements GuaranteeInfo {
        private final Guarantee guarantee;

        @Override
        public Integer getId() {
            return guarantee.getId();
        }

        @Override
        public String getGuaranteeCode() {
            return guarantee.getGuaranteeCode();
        }

        @Override
        public LocalDateTime getActivationDate() {
            return guarantee.getActivationDate();
        }

        @Override
        public LocalDateTime getExpirationDate() {
            return guarantee.getExpirationDate();
        }

        @Override
        public boolean getStatus() {
            return guarantee.isStatus();
        }
    }

    static GuaranteeInfo of (Guarantee guarantee) {
        return new GuaranteeInfoImpl(guarantee);
    }
}
