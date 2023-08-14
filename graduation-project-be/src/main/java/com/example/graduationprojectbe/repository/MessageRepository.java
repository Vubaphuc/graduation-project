package com.example.graduationprojectbe.repository;

import com.example.graduationprojectbe.dto.dto.MessageDto;
import com.example.graduationprojectbe.dto.projection.MessageInfo;
import com.example.graduationprojectbe.entity.Message;
import com.example.graduationprojectbe.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Integer> {

    @Query("select m from Message m where m.room.id = ?1")
    List<Message> getMessageByRoomId(Integer id);

    @Transactional
    @Modifying
    @Query("delete from Message m where m.room = ?1")
    void deleteByRoom(Room room);


}