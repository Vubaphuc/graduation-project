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
public class InformationRepairRequest {
    @NotNull(message = "location cannot be blank")
    private String location;
    private String note;
    @NotNull(message = "status cannot be blank")
    private boolean status;
    @NotNull(message = "components Id cannot be blank")
    private Integer componentsId;
}
