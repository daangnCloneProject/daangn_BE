package com.example.daangn.dto;

import com.example.daangn.model.Post;
import lombok.Getter;

import java.util.List;

@Getter
public class ResponseDto<T> {
    private boolean response;
    private String message;
    private String nickname;
    private List<T> list;
    private PostResultDto post;

    public ResponseDto (boolean response, String message) {
        this.response = response;
        this.message = message;
    }

    public ResponseDto(boolean response, String message, PostResultDto post) {
        this.response = response;
        this.message = message;
        this.post = post;
     }

    public ResponseDto(boolean response, String nickname, List<T> resultDtoList) {
        this.response = response;
        this.nickname = nickname;
        this.list = resultDtoList;
    }

    public ResponseDto(boolean response, PostResultDto postResultDto) {
        this.response = response;
        this.post = postResultDto;
    }

    public ResponseDto(boolean response, Post post) {
        this.post = new PostResultDto();
        this.response = response;
        this.post.setTitle(post.getTitle());
        this.post.setCategory(post.getCategory());
        this.post.setPrice(post.getPrice());
        this.post.setArea(post.getArea());
        this.post.setContent(post.getContent());
        this.post.setImageUrl(post.getImageUrl());
        this.post.setState(post.getState());
        this.post.setAfter(post.getCreatedAt());
        this.post.setUserId(post.getUser().getId());
    }
}
