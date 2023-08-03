package com.example.graduationprojectbe.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateProductRequest {
    @NotNull(message = "customer Id cannot be blank")
    private Integer customerId;
    @NotNull(message = "phone company cannot be blank")
    private String phoneCompany;
    @NotNull(message = "model cannot be blank")
    private String model;
    @NotNull(message = "ime cannot be blank")
    private String ime;
    @NotNull(message = "defect name cannot be blank")
    private String defectName;
    @NotNull(message = "price cannot be blank")
    private double price;
}
