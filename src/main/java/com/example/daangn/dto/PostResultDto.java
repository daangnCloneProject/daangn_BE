package com.example.daangn.dto;

import com.example.daangn.model.AreaEnum;
import com.example.daangn.model.CategoryEnum;
import lombok.Getter;

import java.time.Duration;
import java.time.LocalDateTime;

public class PostResultDto {
    private String title;

    private CategoryEnum category;

    private int price;

    private AreaEnum area;

    private String content;

    private String imageUrl;

    private LocalDateTime after;

    private Long userId;

    public Long getAfter() {
        LocalDateTime today = LocalDateTime.now();
        return Duration.between(today, after).toDays();
    }
}
