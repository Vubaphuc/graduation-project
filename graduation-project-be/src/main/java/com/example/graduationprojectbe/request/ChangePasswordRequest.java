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
public class ChangePasswordRequest {
    @NotNull(message = "oldPassword cannot be blank")
    private String oldPassword;
    @NotNull(message = "newPassword cannot be blank")
    private String newPassword;
    @NotNull(message = "confirmNewPassword cannot be blank")
    private String confirmNewPassword;
}
