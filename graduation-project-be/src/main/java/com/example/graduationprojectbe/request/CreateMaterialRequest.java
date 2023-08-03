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
public class CreateMaterialRequest {
    @NotNull(message = "material code cannot be blank")
    private String materialCode;
    @NotNull(message = "name model cannot be blank")
    private String nameModel;
    @NotNull(message = "vender Id cannot be blank")
    private Integer venderId;
    @NotNull(message = "components Id cannot be blank")
    private Integer componentsId;
    @NotNull(message = "quantity cannot be blank")
    private Integer quantity;
    @NotNull(message = "price cannot be blank")
    private double price;
}
