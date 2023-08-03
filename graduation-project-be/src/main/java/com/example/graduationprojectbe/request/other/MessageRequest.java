package com.example.graduationprojectbe.request.other;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MessageRequest {
    private String content;
    private Integer roomId;
    private Integer userId;
}
