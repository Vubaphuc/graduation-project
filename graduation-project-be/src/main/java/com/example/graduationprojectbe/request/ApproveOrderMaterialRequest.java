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
public class ApproveOrderMaterialRequest {
    @NotNull(message = "material code cannot be blank")
    private String materialCode;
    @NotNull(message = "quantity cannot be blank")
    private Integer quantity;
    @NotNull(message = "status cannot be blank")
    private boolean status;
}
