package com.example.graduationprojectbe.dto.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TotalMaterialDto {
    private long totalImportPrice;
    private long totalExportPrice;
    private long totalMonthExportPrice;
    private long totalTodayExportPrice;
    private long totalImportQuantity;
    private long totalExportQuantity;
    private long totalToDayExportQuantity;
    private long totalMonthExportQuantity;
}
