package com.example.graduationprojectbe.request.update;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateOrderMaterialRequest {
    @NotNull(message = "quantity cannot be blank")
    private Integer quantity;
}
