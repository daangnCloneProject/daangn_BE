package com.example.daangn.dto;

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

    public ResponseDto(boolean response, String nickname, List<T> resultDtoList) {
        this.response = response;
        this.nickname = nickname;
        this.list = resultDtoList;
    }
}
