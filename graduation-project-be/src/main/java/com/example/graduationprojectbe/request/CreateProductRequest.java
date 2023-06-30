package com.example.graduationprojectbe.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateProductRequest {
    private Integer customerId;
    private String phoneCompany;
    private String model;
    private String ime;
    private String defectName;
    private double price;
}
