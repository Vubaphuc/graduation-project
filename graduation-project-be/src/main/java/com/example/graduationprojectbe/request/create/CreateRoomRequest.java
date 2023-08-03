package com.example.graduationprojectbe.request.create;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateRoomRequest {
    private String roomName;
    @NotNull(message = "membersIds cannot be blank")
    private List<Integer> membersIds;
}
