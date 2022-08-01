package com.example.daangn.dto;

import com.example.daangn.model.Message;
import com.example.daangn.model.Room;
import com.example.daangn.model.User;
import lombok.Getter;

import java.util.List;

@Getter
public class RoomResultDto {

    private Long id;
    private Long sellerId;
    private Long buyerId;
    private Long postId;
    private List<Message> messageList;

    public RoomResultDto(Room room) {
        this.id = room.getId();
        this.sellerId = room.getSeller().getId();
        this.buyerId = room.getBuyer().getId();
        this.postId = room.getPost().getId();
        this.messageList = room.getMessageList();
    }

}
