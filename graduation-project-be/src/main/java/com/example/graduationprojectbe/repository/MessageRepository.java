package com.example.graduationprojectbe.repository;

import com.example.graduationprojectbe.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Integer> {

    @Query("select m from Message m where m.room = ?1")
    List<Message> getMessageByRoom(String room);


}