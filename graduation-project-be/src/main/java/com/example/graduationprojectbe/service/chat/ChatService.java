package com.example.graduationprojectbe.service.chat;

import com.example.graduationprojectbe.dto.dto.MessageDto;
import com.example.graduationprojectbe.entity.Message;
import com.example.graduationprojectbe.mapper.DataMapper;
import com.example.graduationprojectbe.repository.MessageRepository;
import com.example.graduationprojectbe.repository.UserRepository;
import com.example.graduationprojectbe.sercurity.ICurrentUserLmpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChatService {
    @Autowired
    private MessageRepository messageRepository;


    // lấy danh sách message theo room id
    public List<MessageDto> getMessageByRoom(String id) {
        List<Message> messages = messageRepository.getMessageByRoom(id);
        if (messages.isEmpty()) {
            return new ArrayList<>();
        }
        return messages.stream().map(DataMapper::toMessage).collect(Collectors.toList());
    }


}
