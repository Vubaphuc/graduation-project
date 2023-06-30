package com.example.graduationprojectbe.dto.dto;

public class ProductTotalOkAndPendingDto {
    private long deliveredCount;
    private long otherCount;

    public ProductTotalOkAndPendingDto(long deliveredCount, long otherCount) {
        this.deliveredCount = deliveredCount;
        this.otherCount = otherCount;
    }

    public long getDeliveredCount() {
        return deliveredCount;
    }

    public void setDeliveredCount(long deliveredCount) {
        this.deliveredCount = deliveredCount;
    }

    public long getOtherCount() {
        return otherCount;
    }

    public void setOtherCount(long otherCount) {
        this.otherCount = otherCount;
    }
}
