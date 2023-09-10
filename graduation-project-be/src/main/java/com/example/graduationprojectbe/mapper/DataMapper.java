package com.example.graduationprojectbe.mapper;


import com.example.graduationprojectbe.dto.dto.MessageDto;
import com.example.graduationprojectbe.dto.dto.TotalProductDto;
import com.example.graduationprojectbe.entity.Message;
import com.example.graduationprojectbe.entity.User;
import com.example.graduationprojectbe.response.DataResponse;

import java.sql.Timestamp;

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
                .room(message.getRoom())
                .userId(message.getSender().getId())
                .content(message.getContent())
                .createdDateTime(Timestamp.valueOf(message.getSentTime()))
                .username(message.getSender().getEmployeeName())
                .build();

        return messageDto;
    }



}
