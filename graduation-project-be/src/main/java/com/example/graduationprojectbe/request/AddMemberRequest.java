package com.example.graduationprojectbe.request;

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
public class AddMemberRequest {
    @NotNull(message = "Room Id Cannot be blank")
    private Integer roomId;
    @NotNull(message = "Member Id cannot be blank")
    private List<Integer> membersIds;
}
