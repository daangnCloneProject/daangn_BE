package com.example.daangn.service;

import com.example.daangn.dto.MessageRequestDto;
import com.example.daangn.dto.MessageResponseDto;
import com.example.daangn.model.Message;
import com.example.daangn.model.Room;
import com.example.daangn.repository.MessageRepository;
import com.example.daangn.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Service
public class MessageService {
    private final MessageRepository messageRepository;
    private final RoomRepository roomRepository;

    public List<MessageResponseDto> getMessages(Long roomId) {
        return messageRepository.findAllByRoomId(roomId);
    }

    @Transactional
    public MessageResponseDto createMessage(MessageRequestDto messageRequestDto, Long roomId) {
        Room room = roomRepository.findById(roomId)
                .orElseThrow( () -> new IllegalArgumentException("방이 존재하지 않습니다."));
        Message message = new Message(messageRequestDto, room);
        messageRepository.save(message);
        return new MessageResponseDto(message);
    }

}
