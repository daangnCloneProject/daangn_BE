package com.example.daangn.controller;

import com.example.daangn.dto.ResponseDto;
import com.example.daangn.dto.SignupRequestDto;
import com.example.daangn.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping("/api/signup")
    public ResponseEntity<ResponseDto> registerUser(@RequestBody SignupRequestDto signupRequestDto){
        return userService.registerUser(signupRequestDto);
    }

    //닉네임 보이기
//    @GetMapping("/api/auth")
//    public AuthResponseDto getAuth(@AuthenticationPrincipal UserDetailsImpl userDetails){
//        String nickname = userDetails.getUser().getNickname();
//
//        AuthResponseDto authResponseDto = new AuthResponseDto(nickname);
//        return authResponseDto;
//    }


}
