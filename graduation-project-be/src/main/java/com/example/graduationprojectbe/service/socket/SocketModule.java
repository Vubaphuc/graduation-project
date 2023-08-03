package com.example.graduationprojectbe.service.socket;

import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIONamespace;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.ConnectListener;
import com.corundumstudio.socketio.listener.DataListener;
import com.corundumstudio.socketio.listener.DisconnectListener;
import com.example.graduationprojectbe.constants.MessageStatus;
import com.example.graduationprojectbe.dto.dto.RoomDto;
import com.example.graduationprojectbe.entity.Room;
import com.example.graduationprojectbe.entity.User;
import com.example.graduationprojectbe.mapper.DataMapper;
import com.example.graduationprojectbe.repository.RoomRepository;
import com.example.graduationprojectbe.repository.UserRepository;
import com.example.graduationprojectbe.request.AddMemberRequest;
import com.example.graduationprojectbe.request.MessageRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@Slf4j
public class SocketModule {

    private final SocketIOServer server;
    private final SocketService socketService;
    private final RoomRepository roomRepository;
    private final UserRepository userRepository;

    public SocketModule(SocketIOServer server, SocketService socketService, RoomRepository roomRepository, UserRepository userRepository) {
        this.roomRepository = roomRepository;
        this.server = server;
        this.socketService = socketService;
        this.userRepository = userRepository;

        server.addConnectListener(onConnected());
        server.addDisconnectListener(onDisconnected());
        server.addEventListener("send_message", MessageRequest.class, onChatReceived());
    }



    private DataListener<MessageRequest> onChatReceived() {
        return (senderClient, data, ackSender) -> {
            socketService.saveMessage(senderClient, data);
        };
    }

    private ConnectListener onConnected() {
        return (client) -> {
            var params = client.getHandshakeData().getUrlParams();
            if (params != null) {
                String roomIdValue = params.get("roomId") != null ? String.join("", params.get("roomId")) : "";
                String userIdValue = params.get("userId") != null ? String.join("", params.get("userId")) : "";

                if (!roomIdValue.isEmpty() && !userIdValue.isEmpty()) {
                    Integer roomId = Integer.valueOf(roomIdValue);
                    Integer userId = Integer.valueOf(userIdValue);
                    String id = String.valueOf(roomId);

                    client.joinRoom(id);
                    log.info("connect Socket ID[{}] - room[{}] - username [{}]  Connected to chat module through", client.getSessionId().toString(), roomId, userId);
                }
            }
        };

    }

    private DisconnectListener onDisconnected() {
        return client -> {
            var params = client.getHandshakeData().getUrlParams();
            if (params != null && !params.isEmpty()) {
                Integer roomId = Integer.valueOf(String.join("", params.get("roomId")));
                Integer usernameId = Integer.valueOf(String.join("", params.get("userId")));
                log.info("disconnect Socket ID[{}] - room[{}] - username [{}]  discnnected to chat module through", client.getSessionId().toString(), roomId, usernameId);
            }
        };
    }


}
