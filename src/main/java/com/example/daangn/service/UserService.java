package com.example.daangn.service;


import com.example.daangn.dto.ResponseDto;
import com.example.daangn.dto.SignupRequestDto;
import com.example.daangn.model.User;
import com.example.daangn.repository.UserRepository;
import com.example.daangn.security.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private final UserDetailsServiceImpl userDetailsService;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, UserDetailsServiceImpl userDetailsService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userDetailsService = userDetailsService;

    }

    //회원가
    public ResponseEntity<ResponseDto<?>> registerUser(SignupRequestDto signupRequestDto) {
        String username = signupRequestDto.getUsername();
        String nickname = signupRequestDto.getNickname();
        String password = signupRequestDto.getPassword();
        String confirmPassword = signupRequestDto.getConfirmPassword();

        Optional<User> usernameUserFound = userRepository.findByUsername(username);
        if(usernameUserFound.isPresent()){
            throw new IllegalArgumentException("중복된 username이 존재합니다");
        }

        Optional<User> nicknameUserFound = userRepository.findByNickname(nickname);
        if(nicknameUserFound.isPresent()){
            throw new IllegalArgumentException("중복된 nickname이 존재합니다");
        }
        if(!password.equals(confirmPassword)){
            throw new IllegalArgumentException("비밀번호와 비밀번호 확인이 일치하지 않습니다.");
        }
        User user = new User(nickname,username,passwordEncoder.encode(password));

        userRepository.save(user);
        return new ResponseEntity<>(new ResponseDto<>(true, "회원가입 성공"), HttpStatus.OK);

    }
//    유저 목록
//    public Authentication getAuthentication(String username) {
//        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
//        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
//    }

}
