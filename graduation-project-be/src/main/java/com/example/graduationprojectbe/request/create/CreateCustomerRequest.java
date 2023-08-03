package com.example.graduationprojectbe.request.create;

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
public class CreateCustomerRequest {
    @NotNull(message = "customer name cannot be blank")
    private String customerName;
    @NotNull(message = "customer email cannot be blank")
    @Email(message = "Invalid email")
    private String customerEmail;
    @NotNull(message = "customer address cannot be blank")
    private String customerAddress;
    @NotNull(message = "phone number cannot be blank")
    @Pattern(regexp = "^(84|0[3|5|7|8|9])([0-9]{8})$", message = "invalid phone number")
    private String phoneNumber;
}
