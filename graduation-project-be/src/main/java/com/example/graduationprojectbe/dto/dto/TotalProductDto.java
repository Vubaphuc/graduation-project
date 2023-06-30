package com.example.graduationprojectbe.dto.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TotalProductDto {
    private String employeeCode;
    private String employeeName;
    private long totalProductOk;
    private long totalProductPending;
}
