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
public class InformationEngineerRequest {
    @NotNull(message = "employee code cannot be blank")
    private String employeeCode;
}
