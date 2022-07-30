package com.example.daangn.dto;

import com.example.daangn.model.AreaEnum;
import com.example.daangn.model.CategoryEnum;
import com.example.daangn.model.StateEnum;
import lombok.Getter;
import lombok.Setter;

import java.time.Duration;
import java.time.LocalDateTime;

@Getter
@Setter
public class PostResultDto {
    private String title;

    private CategoryEnum category;

    private int price;

    private AreaEnum area;

    private String content;

    private String imageUrl;

    private StateEnum state;

    private LocalDateTime after;

    private Long userId;

    public Long getAfter() {
        LocalDateTime today = LocalDateTime.now();
        return Duration.between(after,today).toDays();
    }
}
