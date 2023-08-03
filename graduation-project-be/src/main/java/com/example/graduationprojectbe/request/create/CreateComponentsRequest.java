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
public class CreateComponentsRequest {
    @NotNull(message = "name cannot be blank")
    private String name;
    @NotNull(message = "warrantyPeriod cannot be blank")
    private Integer warrantyPeriod;
}
