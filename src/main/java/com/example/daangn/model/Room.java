package com.example.daangn.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
@AllArgsConstructor
public class Room {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "SELLER_ID", nullable = false)
    private User seller;

    @ManyToOne
    @JoinColumn(name = "BUYER_ID", nullable = false)
    private User buyer;

    @ManyToOne
    @JoinColumn(name = "POST_ID", nullable = false)
    private Post post;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "room")
    private List<Message> messageList;

    public Room(Post post, User seller , User user){
        this.post = post;
        this.seller = seller;
        this.buyer = user;
    }
}
