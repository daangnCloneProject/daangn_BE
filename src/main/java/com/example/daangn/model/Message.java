package com.example.daangn.model;

import com.example.daangn.dto.MessageRequestDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@AllArgsConstructor
public class Message extends TimeStamped{
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(nullable = false)
    private String content;

    @ManyToOne
    @JoinColumn(name = "ROOM_ID", nullable = false)
    private Room room;

    @Column(nullable = false)
    private String nickname;

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", room=" + room +
                ", nickname='" + nickname + '\'' +
                '}';
    }

    public Message(MessageRequestDto messageRequestDto, Room room) {
        this.content = messageRequestDto.getMessage();
        this.room = room;
        this.nickname = messageRequestDto.getNickname();
    }
}
