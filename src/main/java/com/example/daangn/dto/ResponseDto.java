package com.example.daangn.dto;

import com.example.daangn.model.Post;

import java.util.List;

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
}
