package com.example.daangn.model;

import com.example.daangn.dto.PostRequestDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
@AllArgsConstructor
public class Post extends TimeStamped{
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private CategoryEnum category;

    @Column(nullable = false)
    private int price;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private AreaEnum area;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private String imageUrl;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private StateEnum state;

    @ManyToOne
    @JoinColumn(name = "USER_ID", nullable = false)
    private User user;

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "post")
    private List<Like> likeList;

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "post")
    private List<Room> roomList;

    public Post(PostRequestDto requestDto, User user) {
        this.title = requestDto.getTitle();
        this.category = requestDto.getCategory();
        this.price = requestDto.getPrice();
        this.area = requestDto.getArea();
        this.content = requestDto.getContent();
        this.imageUrl = requestDto.getImageUrl();
        this.state = StateEnum.from("SALE");
        this.user = user;
        this.likeList = new ArrayList<>();
        this.roomList = new ArrayList<>();
    }
    public Post(Long id, String title, CategoryEnum category, int price, AreaEnum area, String content, String imageUrl, StateEnum state, User user) {
        this.id = id;
        this.title = title;
        this.category = category;
        this.price = price;
        this.area = area;
        this.content = content;
        this.imageUrl = imageUrl;
        this.state = state;
        this.user = user;
        this.likeList = new ArrayList<>();
        this.roomList = new ArrayList<>();
    }
    public void updatePost(PostRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.category = requestDto.getCategory();
        this.price = requestDto.getPrice();
        this.area = requestDto.getArea();
        this.content = requestDto.getContent();
        this.imageUrl = requestDto.getImageUrl();
    }
}
