package com.example.graduationprojectbe.controller.chat;

import com.corundumstudio.socketio.SocketIOServer;
import com.example.graduationprojectbe.repository.MessageRepository;
import com.example.graduationprojectbe.repository.UserRepository;
import com.example.graduationprojectbe.sercurity.ICurrentUserLmpl;
import com.example.graduationprojectbe.service.chat.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("chat/api/v1")
public class ChatController {
    @Autowired
    private ChatService chatService;

    // lấy danh sách message theo room
    @GetMapping("message/{id}")
    public ResponseEntity<?> getMessageByRoom(@PathVariable String id) {
        return ResponseEntity.ok(chatService.getMessageByRoom(id));
    }


}
