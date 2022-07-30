package com.example.daangn.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SignupRequestDto {
    private String username;

    private String password;

    private String confirmPassword;

    private String nickname;

}
