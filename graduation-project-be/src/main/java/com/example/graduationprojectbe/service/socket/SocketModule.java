package com.example.graduationprojectbe.service.socket;

import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.ConnectListener;
import com.corundumstudio.socketio.listener.DataListener;
import com.corundumstudio.socketio.listener.DisconnectListener;
import com.example.graduationprojectbe.repository.UserRepository;
import com.example.graduationprojectbe.request.other.MessageRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class SocketModule {

    private final SocketIOServer server;
    private final SocketService socketService;

    public SocketModule(SocketIOServer server, SocketService socketService ) {
        this.server = server;
        this.socketService = socketService;

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

                String roomValue = params.get("room") != null ? String.join("", params.get("room")) : "";
                String userIdValue = params.get("userId") != null ? String.join("", params.get("userId")) : "";

                if (!roomValue.isEmpty() && !userIdValue.isEmpty()) {
                    Integer userId = Integer.valueOf(userIdValue);

                    client.joinRoom(roomValue);
                    log.info("connect Socket ID[{}] - room[{}] - username [{}]  Connected to chat module through", client.getSessionId().toString(), roomValue, userId);


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
