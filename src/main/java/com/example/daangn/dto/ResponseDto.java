package com.example.daangn.dto;

import com.example.daangn.model.Post;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Slice;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL) //null인 데이터는 json 결과에 나오지 않음
public class ResponseDto<T> {
    private boolean response;
    private String message;
    private String nickname;
    private Slice<PostResultDto> list;
    private PostResultDto post;

    public ResponseDto (boolean response, String message) {
        this.response = response;
        this.message = message;
    }

    public ResponseDto(boolean response, PostResultDto postResultDto) {
        this.response = response;
        this.post = postResultDto;
    }

    public ResponseDto(boolean response, String nickname, Slice<PostResultDto> postResultDtoSlice) {
        this.response = response;
        this.nickname = nickname;
        this.list = postResultDtoSlice;
    }

    public ResponseDto(boolean response, Slice<PostResultDto> postResultDtoSlice) {
        this.response = response;
        this.list = postResultDtoSlice;
    }
}
