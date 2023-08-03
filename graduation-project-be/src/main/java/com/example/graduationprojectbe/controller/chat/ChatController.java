package com.example.graduationprojectbe.controller.chat;

import com.corundumstudio.socketio.SocketIOServer;
import com.example.graduationprojectbe.repository.MessageRepository;
import com.example.graduationprojectbe.repository.RoomRepository;
import com.example.graduationprojectbe.repository.UserRepository;
import com.example.graduationprojectbe.request.other.AddMemberRequest;
import com.example.graduationprojectbe.request.create.CreateRoomRequest;
import com.example.graduationprojectbe.sercurity.ICurrentUserLmpl;
import com.example.graduationprojectbe.service.chat.ChatService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("chat/api/v1")
public class ChatController {
    @Autowired
    private ChatService chatService;
    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private SocketIOServer server;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ICurrentUserLmpl iCurrentUserLmpl;



    // lấy danh sách message theo room id
    @GetMapping("message/{id}")
    public ResponseEntity<?> getMessageByRoomId(@PathVariable Integer id) {
        return ResponseEntity.ok(chatService.getMessageByRoomId(id));
    }

    // lấy danh sách member theo id room
    @GetMapping("room/{id}/members")
    public ResponseEntity<?> getMemberByRoomId(@PathVariable Integer id) {
        return ResponseEntity.ok(chatService.getMemberByRoomId(id));
    }

    // lấy danh sách room theo user id
    @GetMapping("room/members/{id}")
    public ResponseEntity<?> getRoomByUserId(@PathVariable Integer id) {
        return ResponseEntity.ok(chatService.getRoomByUserId(id));
    }

    // lấy all room
    @GetMapping("room")
    public ResponseEntity<?> getRoomAll () {
        return ResponseEntity.ok(chatService.getRoomAll());
    }


    // thêm thành viên mới vào room
    @PutMapping("room/members")
    public ResponseEntity<?> addMemberToRoom (@Valid @RequestBody AddMemberRequest request) {
        return ResponseEntity.ok(chatService.addMemberToRoom(request));
    }


    // xóa room hoặc thoát room
    @DeleteMapping("room/{id}")
    public ResponseEntity<?> deleteRoomChat(@PathVariable Integer id) {
        return ResponseEntity.ok(chatService.deleteRoomChat(id));
    }


    @PostMapping("/createRoom")
    public ResponseEntity<?> createRoom(@Valid @RequestBody CreateRoomRequest request) {
        return ResponseEntity.ok(chatService.createRoom(request));
    }


}
