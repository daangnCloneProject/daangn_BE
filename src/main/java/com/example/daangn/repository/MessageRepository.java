package com.example.daangn.repository;

import com.example.daangn.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findAllByRoomId(Long roomId);

    void deleteAllByRoomId(Long roomId);
}
