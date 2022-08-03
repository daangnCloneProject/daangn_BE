package com.example.daangn.dto;

import com.example.daangn.model.Message;
import com.example.daangn.model.User;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class MessageResponseDto {
    private LocalDateTime createdAt;
    private String nickname;
    private String message;

    public MessageResponseDto(Message message) {
        this.createdAt = message.getCreatedAt();
        this.nickname = message.getNickname();
        this.message = message.getContent();
    }
}
