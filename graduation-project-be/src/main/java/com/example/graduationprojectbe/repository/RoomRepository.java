package com.example.graduationprojectbe.repository;

import com.example.graduationprojectbe.dto.projection.RoomInfo;
import com.example.graduationprojectbe.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Integer> {
    @Query("select rm from Room rm")
    List<RoomInfo> getRoomAll();

    @Query("select rm from Room rm join rm.users u where u.id = ?1 ")
    List<Room> getRoomByUserId(Integer id);

    @Modifying
    @Query("delete from Room rm where rm.id = :id ")
    void deleteById(@Param("id") Integer id);
}