package com.example.graduationprojectbe.request.update;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateReceiptRequest {
    @NotNull(message = "Pay Date cannot be blank")
    private LocalDateTime payDate;
}
