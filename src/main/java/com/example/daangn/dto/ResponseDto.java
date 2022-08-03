package com.example.daangn.dto;

import com.example.daangn.model.Post;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Slice;

@Getter
@Setter
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL) //null인 데이터는 json 결과에 나오지 않음
public class ResponseDto {
    private boolean response;
    private String message;
    private String nickname;
    private Slice<PostResultDto> list;
    private PostResultDto post;

    private Slice<Post> posts;
    private Long roomId;

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

    public ResponseDto(boolean response, String message, Long roomId) {
        this.response = response;
        this.message = message;
        this.roomId = roomId;
    }
}
