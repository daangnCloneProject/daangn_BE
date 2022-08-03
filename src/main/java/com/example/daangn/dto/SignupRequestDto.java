package com.example.daangn.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SignupRequestDto {
    private String username;

    private String password;

    private String confirmPassword;

    private String nickname;

}
