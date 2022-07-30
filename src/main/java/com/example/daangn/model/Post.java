package com.example.daangn.model;

import com.example.daangn.dto.PostRequestDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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

    public Post(PostRequestDto requestDto, User user) {
        this.title = requestDto.getTitle();
        this.category = requestDto.getCategory();
        this.price = requestDto.getPrice();
        this.area = requestDto.getArea();
        this.content = requestDto.getContent();
        this.imageUrl = requestDto.getImageUrl();
        this.state = StateEnum.from("SALE");
        this.user = user;
    }

    public void updatePost(PostRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.category = requestDto.getCategory();
        this.price = requestDto.getPrice();
        this.area = requestDto.getArea();
        this.content = requestDto.getContent();
        this.imageUrl = requestDto.getImageUrl();
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", category=" + category +
                ", price=" + price +
                ", area=" + area +
                ", content='" + content + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", state=" + state +
                ", user=" + user +
                '}';
    }
}
