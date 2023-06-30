package com.example.graduationprojectbe.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AdminCreateEmployeeRequest {
    @NotNull(message = "Full Name cannot be blank")
    private String fullName;
    @Email(message = "Invalid email")
    @NotNull(message = "Email cannot be blank")
    private String email;
    @Pattern(regexp = "^(84|0[3|5|7|8|9])([0-9]{8})$", message = "invalid phone number")
    @NotNull(message = "Phone Number cannot be blank")
    private String phoneNumber;
    @NotNull(message = "Password cannot be blank")
    private String password;
    @NotNull(message = "Address cannot be blank")
    private String address;
    @NotNull(message = "Roles cannot be blank")
    private List<Integer> roleIds;
}
