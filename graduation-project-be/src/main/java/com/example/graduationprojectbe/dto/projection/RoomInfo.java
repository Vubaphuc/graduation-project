package com.example.graduationprojectbe.dto.projection;

import com.example.graduationprojectbe.entity.Role;
import com.example.graduationprojectbe.entity.Room;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Projection for {@link com.example.graduationprojectbe.entity.Room}
 */
public interface RoomInfo {
    Integer getId();

    String getName();

    EmployeeInfo getAdmin();

    List<EmployeeInfo> getUsers();

    @RequiredArgsConstructor
    class RoomInfoImpl implements RoomInfo {
        private final Room room;


        @Override
        public Integer getId() {
            return room.getId();
        }

        @Override
        public String getName() {
            return room.getName();
        }

        @Override
        public EmployeeInfo getAdmin() {
            return EmployeeInfo.of(room.getAdmin());
        }

        @Override
        public List<EmployeeInfo> getUsers() {
            return room.getUsers()
                    .stream()
                    .map(EmployeeInfo::of)
                    .collect(Collectors.toList());
        }
    }

    static RoomInfo of(Room room) {
        return new RoomInfoImpl(room);
    }
}