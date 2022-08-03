package com.example.daangn.dto;

import com.example.daangn.model.AreaEnum;
import com.example.daangn.model.CategoryEnum;
import com.example.daangn.model.StateEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class PostRequestDto {
    private String title;

    private CategoryEnum category;

    private int price;

    private AreaEnum area;

    private String content;

    private String imageUrl;

    private StateEnum state;
}
