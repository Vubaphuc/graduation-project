package com.example.graduationprojectbe.mapper;


import com.example.graduationprojectbe.dto.dto.TotalProductDto;
import com.example.graduationprojectbe.entity.User;
import com.example.graduationprojectbe.response.DataResponse;

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
}
