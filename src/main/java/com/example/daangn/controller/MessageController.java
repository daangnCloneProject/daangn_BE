package com.example.daangn.controller;


import com.example.daangn.dto.MessageRequestDto;
import com.example.daangn.dto.MessageResponseDto;
import com.example.daangn.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class MessageController {

    private final SimpMessagingTemplate template;
    private final MessageService messageService;

    @GetMapping("/api/message/{roomId}")
    public ResponseEntity<List<MessageResponseDto>> getMessages(@PathVariable Long roomId) {
        return new ResponseEntity<>(messageService.getMessages(roomId), HttpStatus.OK);
    }

    @MessageMapping("/message/{roomId}")
    public ResponseEntity<MessageResponseDto> createMessage(MessageRequestDto messageRequestDto, @DestinationVariable Long roomId) {
        MessageResponseDto messageResponseDto = messageService.createMessage(messageRequestDto, roomId);
        template.convertAndSend("/sub/room/" + roomId, messageResponseDto);
        return new ResponseEntity<>(messageResponseDto, HttpStatus.OK);
    }

}
