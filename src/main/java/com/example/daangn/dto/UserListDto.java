package com.example.daangn.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserListDto {
    private Long userId;
    private String username;
    private String nickname;
}
