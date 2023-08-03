package com.example.graduationprojectbe.mapper;


import com.example.graduationprojectbe.dto.dto.MessageDto;
import com.example.graduationprojectbe.dto.dto.RoomDto;
import com.example.graduationprojectbe.dto.dto.TotalProductDto;
import com.example.graduationprojectbe.entity.Message;
import com.example.graduationprojectbe.entity.Room;
import com.example.graduationprojectbe.entity.User;
import com.example.graduationprojectbe.response.DataResponse;

import java.sql.Timestamp;
import java.util.stream.Collectors;

public class DataMapper {
    public static DataResponse toDataResponse (Integer id, String name) {
        DataResponse dataResponse = DataResponse.builder()
                .name(name)
                .id(id)
                .build();

        return dataResponse;
    }


    public static TotalProductDto toTotalProductDto (User user, long totalOk, long totalPending){

        TotalProductDto totalProductDto = TotalProductDto.builder()
                .employeeCode(user.getEmployeeCode())
                .employeeName(user.getEmployeeName())
                .totalProductOk(totalOk)
                .totalProductPending(totalPending)
                .build();

        return totalProductDto;
    }


    public static MessageDto toMessage (Message message){

        MessageDto messageDto = MessageDto.builder()
                .userId(message.getSender() != null ? message.getSender().getId() : 0)
                .content(message.getContent())
                .createdDateTime(Timestamp.valueOf(message.getSentTime()))
                .username(message.getSender() != null ? message.getSender().getEmployeeName() : "")
                .build();

        return messageDto;
    }


    public static RoomDto toRooms (Room room){

        RoomDto roomDto = RoomDto.builder()
                .id(room.getId())
                .name(room.getName())
                .members(room.getUsers().stream().map(UserMapper::toUserDto).collect(Collectors.toList()))
                .build();

        return roomDto;
    }

}
