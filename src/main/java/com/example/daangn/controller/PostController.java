package com.example.daangn.controller;

import com.example.daangn.dto.PostRequestDto;
import com.example.daangn.dto.ResponseDto;
import com.example.daangn.security.UserDetailsImpl;
import com.example.daangn.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
public class PostController {
    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/api/post")
    public ResponseEntity<ResponseDto<?>> createPost(@RequestBody PostRequestDto requestDto,
                                                     @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        return postService.createPost(requestDto, userDetails.getUser());
    }

    @GetMapping("/api/post/{postId}")
    public ResponseEntity<ResponseDto<?>> readPost(@PathVariable Long postId){
        return postService.readPost(postId);
    }

    @PutMapping("/api/post/{postId}")
    public ResponseEntity<ResponseDto<?>> editPost(@PathVariable Long postId,
                         @RequestBody PostRequestDto requestDto,
                         @AuthenticationPrincipal UserDetailsImpl userDetails){
        return postService.editPost(postId, userDetails.getUser(), requestDto);
    }

    @DeleteMapping("/api/post/{postId}")
    public ResponseEntity<ResponseDto<?>> deletePost(@PathVariable Long postId,
                           @AuthenticationPrincipal UserDetailsImpl userDetails){
        return postService.deletePost(postId, userDetails.getUser());
    }

//    @GetMapping("/api/posts")
//    public ResponseEntity<ResponseDto<?>> allPost(@RequestParam String category,@RequestParam
//                                                  int page, @RequestParam int size){
//        return postService.allPosts(category, page, size);
//
//    }


}
