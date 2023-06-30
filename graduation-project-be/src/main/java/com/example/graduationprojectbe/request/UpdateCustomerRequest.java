package com.example.graduationprojectbe.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCustomerRequest {
    private String customerName;
    private String customerEmail;
    private String customerAddress;
    private String phoneNumber;
}
