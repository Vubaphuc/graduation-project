package com.example.graduationprojectbe.dto.dto;

import lombok.*;

import java.util.Date;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class MessageDto {
    private String content;
    private String username;
    private Date createdDateTime;
}
