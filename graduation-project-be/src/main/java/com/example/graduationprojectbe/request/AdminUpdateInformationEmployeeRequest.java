package com.example.graduationprojectbe.request;

import jakarta.validation.constraints.Email;
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
public class AdminUpdateInformationEmployeeRequest {
    @NotNull(message = "Name cannot be blank")
    private String fullName;
    @NotNull(message = "email cannot be blank")
    @Email(message = "Invalid email")
    private String email;
    @Pattern(regexp = "^(84|0[3|5|7|8|9])([0-9]{8})$", message = "invalid phone number")
    @NotNull(message = "phone cannot be blank")
    private String phone;
    @NotNull(message = "address cannot be blank")
    private String address;
}
