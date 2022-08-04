package com.example.daangn.dto;

import com.example.daangn.model.Message;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class MessageResponseDto {
    private LocalDateTime createdAt;
    private String nickname;
    private String message;

    public MessageResponseDto(Message message) {
        this.createdAt = message.getCreatedAt();
        this.nickname = message.getNickname();
        this.message = message.getMessage();
    }
}
