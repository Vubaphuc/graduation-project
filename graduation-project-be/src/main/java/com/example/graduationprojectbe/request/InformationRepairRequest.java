package com.example.graduationprojectbe.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InformationRepairRequest {
    private String location;
    private String note;
    private boolean status;
    private Integer componentsId;
}
