package com.example.daangn.repository;

import com.example.daangn.dto.MessageResponseDto;
import com.example.daangn.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {
    List<MessageResponseDto> findAllByRoomId(Long roomId);

    void deleteAllByRoomId(Long roomId);
}
