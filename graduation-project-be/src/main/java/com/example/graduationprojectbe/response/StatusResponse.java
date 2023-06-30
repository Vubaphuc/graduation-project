package com.example.graduationprojectbe.response;

import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StatusResponse {
    private HttpStatus status;
    private String message;
    private DataResponse data;

}
