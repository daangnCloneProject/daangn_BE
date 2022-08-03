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
    private String sellerNickname;
    private Long buyerId;
    private String buyerNickname;
    private Long postId;
    private List<MessageResponseDto> messageResponseDtoList;

    public RoomResultDto(Room room) {
        this.id = room.getId();
        this.sellerId = room.getSeller().getId();
        this.sellerNickname = room.getSeller().getNickname();
        this.buyerId = room.getBuyer().getId();
        this.buyerNickname = room.getBuyer().getNickname();
        this.postId = room.getPost().getId();
    }

    public RoomResultDto(Room room, List<MessageResponseDto> messageResponseDtoList) {
        this.id = room.getId();
        this.sellerId = room.getSeller().getId();
        this.sellerNickname = room.getSeller().getNickname();
        this.buyerId = room.getBuyer().getId();
        this.buyerNickname = room.getBuyer().getNickname();
        this.postId = room.getPost().getId();
        this.messageResponseDtoList = messageResponseDtoList;
    }

}
