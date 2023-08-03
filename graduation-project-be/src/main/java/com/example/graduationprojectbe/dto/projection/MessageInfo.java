package com.example.graduationprojectbe.dto.projection;

import com.example.graduationprojectbe.entity.Message;
import com.example.graduationprojectbe.entity.Role;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

/**
 * Projection for {@link com.example.graduationprojectbe.entity.Message}
 */
public interface MessageInfo {
    Integer getId();

    String getContent();

    LocalDateTime getSentTime();

    EmployeeInfo getSender();


    @RequiredArgsConstructor
    class MessageInfoImpl implements MessageInfo {
        private final Message message;


        @Override
        public Integer getId() {
            return message.getId();
        }

        @Override
        public String getContent() {
            return message.getContent();
        }

        @Override
        public LocalDateTime getSentTime() {
            return message.getSentTime();
        }

        @Override
        public EmployeeInfo getSender() {
            return EmployeeInfo.of(message.getSender());
        }
    }

    static MessageInfo of(Message message) {
        return new MessageInfoImpl(message);
    }
}