package com.example.daangn.model;

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
}
