package com.example.daangn.service;

import com.example.daangn.dto.SignupRequestDto;
import com.example.daangn.model.User;
import com.example.daangn.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
class UserServiceTest {

    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;

    private String username;
    private String password;
    private String confirmPassword;
    private String nickname;


    @BeforeEach
    void setup() {
        username = "test12323";
        password = "1234qwer";
        confirmPassword = "1234qwer";
        nickname = "test";
    }

    @Test
    void 회원가입() {
        //given
        SignupRequestDto signupRequestDto = new SignupRequestDto(
                username, password, confirmPassword, nickname
        );

        //when
        userService.registerUser(signupRequestDto);

        //then
        User usernameUserFound = userRepository.findByUsername("test12323").get();
        assertEquals(signupRequestDto.getUsername(), usernameUserFound.getUsername());
        assertEquals(signupRequestDto.getNickname(), usernameUserFound.getNickname());
    }

}