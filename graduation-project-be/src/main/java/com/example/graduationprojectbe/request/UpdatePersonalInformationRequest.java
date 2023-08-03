package com.example.graduationprojectbe.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdatePersonalInformationRequest {
    @NotNull(message = "name cannot be blank")
    private String fullName;
    @NotNull(message = "phone cannot be blank")
    @Pattern(regexp = "^(84|0[3|5|7|8|9])([0-9]{8})$", message = "invalid phone number")
    private String phone;
    @NotNull(message = "address cannot be blank")
    private String address;
}
