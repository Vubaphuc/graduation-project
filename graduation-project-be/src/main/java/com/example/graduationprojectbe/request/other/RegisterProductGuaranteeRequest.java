package com.example.graduationprojectbe.request.other;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterProductGuaranteeRequest {
    @NotNull(message = "id cannot be blank")
    private Integer id;
    @NotNull(message = "defectName cannot be blank")
    private String defectName;
    @NotNull(message = "cause cannot be blank")
    private String cause;
    @NotNull(message = "price cannot be blank")
    private double price;
}
