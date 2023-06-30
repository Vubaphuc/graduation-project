package com.example.graduationprojectbe.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateMaterialRequest {
    private String materialCode;
    private String nameModel;
    private Integer venderId;
    private Integer componentsId;
    private Integer quantity;
    private double price;
}
