package com.example.daangn.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
}
