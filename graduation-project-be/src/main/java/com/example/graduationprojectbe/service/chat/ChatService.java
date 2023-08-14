package com.example.graduationprojectbe.service.chat;

import com.example.graduationprojectbe.dto.dto.MessageDto;
import com.example.graduationprojectbe.dto.dto.RoomDto;
import com.example.graduationprojectbe.dto.projection.RoomInfo;
import com.example.graduationprojectbe.entity.Message;
import com.example.graduationprojectbe.entity.Room;
import com.example.graduationprojectbe.entity.User;
import com.example.graduationprojectbe.exception.NotFoundException;
import com.example.graduationprojectbe.mapper.DataMapper;
import com.example.graduationprojectbe.repository.MessageRepository;
import com.example.graduationprojectbe.repository.RoomRepository;
import com.example.graduationprojectbe.repository.UserRepository;
import com.example.graduationprojectbe.request.other.AddMemberRequest;
import com.example.graduationprojectbe.request.create.CreateRoomRequest;
import com.example.graduationprojectbe.response.StatusResponse;
import com.example.graduationprojectbe.sercurity.ICurrentUserLmpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChatService {
    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ICurrentUserLmpl iCurrentUserLmpl;
    @Autowired
    private RoomRepository roomRepository;




    // lấy danh sách message theo room id
    public List<MessageDto> getMessageByRoomId(Integer id) {
        List<Message> messages = messageRepository.getMessageByRoomId(id);
        if (messages.isEmpty()) {
            return new ArrayList<>();
        }
        return messages.stream().map(DataMapper::toMessage).collect(Collectors.toList());
    }


    public RoomDto getMemberByRoomId(Integer id) {
        Room room = roomRepository.findById(id).orElseThrow(() -> new NotFoundException("Not Found With Id: " + id));

        return DataMapper.toRooms(room);
    }

    public List<RoomInfo> getRoomAll() {
        return roomRepository.getRoomAll();
    }

    public List<RoomDto> getRoomByUserId(Integer id) {

        List<Room> rooms = roomRepository.getRoomByUserId(id);

        if (rooms.isEmpty()) {
            return  new ArrayList<>();
        }

        return rooms.stream().map(DataMapper::toRooms).toList();
    }

    // tạo room mới
    public StatusResponse createRoom(CreateRoomRequest request) {

        User admin = iCurrentUserLmpl.getUser();
        List<User> users = userRepository.findAllById(request.getMembersIds());

        users.add(admin);

        Room room = Room.builder()
                .name(request.getRoomName())
                .admin(admin)
                .users(users)
                .build();

        roomRepository.save(room);

        return new StatusResponse(HttpStatus.CREATED, "Create Room success", DataMapper.toDataResponse(room.getId(), room.getName()));
    }

    // thêm thành viên vào room
    public StatusResponse addMemberToRoom(AddMemberRequest request) {

        Room room = roomRepository.findById(request.getRoomId()).orElseThrow(() -> new NotFoundException("Not Found With ID: " + request.getRoomId()));

        List<User> members = userRepository.findAllById(request.getMembersIds());

        room.getUsers().addAll(members);
        roomRepository.save(room);

        return new StatusResponse(HttpStatus.OK, "Create Room success", DataMapper.toDataResponse(room.getId(), room.getName()));
    }

    // xóa room hoặc thoát room
    public StatusResponse deleteRoomChat(Integer id) {

        Room room = roomRepository.findById(id).orElseThrow(() -> new NotFoundException("Not Found With ID: " + id));
        User user = iCurrentUserLmpl.getUser();

        if (room.getAdmin().equals(user)) {
            messageRepository.deleteByRoom(room);
            roomRepository.deleteById(room.getId());
        } else  {
            room.getUsers().remove(user);
            roomRepository.save(room);
        }
        return new StatusResponse(HttpStatus.NO_CONTENT, "Delete Room success", DataMapper.toDataResponse(room.getId(), room.getName()));
    }



}
