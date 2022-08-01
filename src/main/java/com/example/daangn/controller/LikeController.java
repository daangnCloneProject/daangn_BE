package com.example.daangn.controller;

import com.example.daangn.dto.ResponseDto;
import com.example.daangn.model.User;
import com.example.daangn.security.UserDetailsImpl;
import com.example.daangn.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LikeController {
    private final LikeService likeService;

    @Autowired
    public LikeController(LikeService likeService) {
        this.likeService = likeService;
    }

    @PostMapping("/api/like/{postId}")
    public ResponseEntity<ResponseDto<?>> createLike(
            @PathVariable Long postId,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ){
        User user = userDetails.getUser();
        return likeService.createLike(postId, user);
    }

    @DeleteMapping("/api/like/{postId}")
    public ResponseEntity<ResponseDto<?>> deleteLike(
            @PathVariable Long postId,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ){
        Long userId = userDetails.getUser().getId();
        return likeService.deleteLike(postId, userId);
    }
}
