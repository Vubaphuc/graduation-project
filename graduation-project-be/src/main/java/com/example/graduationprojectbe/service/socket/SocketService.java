package com.example.graduationprojectbe.service.socket;


import com.corundumstudio.socketio.SocketIOClient;
import com.example.graduationprojectbe.dto.dto.MessageDto;
import com.example.graduationprojectbe.entity.Message;
import com.example.graduationprojectbe.entity.User;
import com.example.graduationprojectbe.mapper.DataMapper;
import com.example.graduationprojectbe.repository.MessageRepository;
import com.example.graduationprojectbe.repository.UserRepository;
import com.example.graduationprojectbe.request.other.MessageRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class SocketService {

    private final MessageRepository messageRepository;
    private final UserRepository userRepository;



    public void sendSocketMessage(SocketIOClient senderClient, MessageDto message, String room) {
        log.info("vao day đến gân đây");
        for (
                SocketIOClient client : senderClient.getNamespace().getRoomOperations(room).getClients()) {
            if (!client.getSessionId().equals(senderClient.getSessionId())) {
                log.info("vao day read_message");
                client.sendEvent("read_message",
                        message);
            }
        }
    }


    public void saveMessage(SocketIOClient senderClient, MessageRequest request) {

        User user = userRepository.findById(request.getUserId()).orElse(null);

        Message message = Message.builder()
                .sentTime(LocalDateTime.now())
                .sender(user)
                .content(request.getContent())
                .room(request.getRoom())
                .build();

        messageRepository.save(message);

        log.info("vao saveMessage");

        sendSocketMessage(senderClient, DataMapper.toMessage(message), request.getRoom());

    }

}
