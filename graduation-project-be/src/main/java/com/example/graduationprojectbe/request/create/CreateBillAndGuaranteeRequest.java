package com.example.graduationprojectbe.request.create;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateBillAndGuaranteeRequest {
    @NotNull(message = "product Id cannot be blank")
    private Integer productId;
}
