package com.example.daangn.service;

import com.example.daangn.dto.PostRequestDto;
import com.example.daangn.dto.PostResultDto;
import com.example.daangn.dto.ResponseDto;
import com.example.daangn.model.Post;
import com.example.daangn.model.User;
import com.example.daangn.repository.like.LikeRepositoryImpl;
import com.example.daangn.repository.post.PostRepository;
import com.example.daangn.repository.post.PostRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final PostRepositoryImpl postRepositoryImpl;
    private final LikeRepositoryImpl likeRepository;


    @Transactional
    public ResponseEntity<ResponseDto<?>> createPost(PostRequestDto requestDto, User user) {
        Post post = new Post(requestDto, user);
        postRepository.save(post);
        return new ResponseEntity<>(new ResponseDto<>(true, "게시글 생성 성공"), HttpStatus.OK);
    }


    public ResponseEntity<ResponseDto<?>> readPost(Long postId, Long userId) {
        PostResultDto postResultDto = postRepositoryImpl.findByPostId(postId);
        postResultDto.setIsLiked(likeRepository.findByPostIdAndUserId(postId, userId) != null);

        return new ResponseEntity<>(new ResponseDto<>(
                true,
                postResultDto
        ), HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity<ResponseDto<?>> editPost(Long postId, User user, PostRequestDto requestDto) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 포스트입니다."));
        if(!user.getId().equals(post.getUser().getId())){
            throw new IllegalArgumentException("접근 권한이 없는 사용자입니다.");
        }
        post.updatePost(requestDto);
        postRepository.save(post);
        return new ResponseEntity<>(new ResponseDto<>(true, "게시글 수정 성공"), HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity<ResponseDto<?>> deletePost(Long postId, User user) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 포스트입니다."));
        if(!user.getId().equals(post.getUser().getId())){
            throw new IllegalArgumentException("접근 권한이 없는 사용자입니다.");
        }
        postRepository.deleteById(postId);
        return new ResponseEntity<>(new ResponseDto<>(true, "게시글 삭제 성공"), HttpStatus.OK);
    }

    public ResponseEntity<ResponseDto<?>> readPosts(String category, String area, int page, int size) {
        Pageable pageable = PageRequest.of(page,size);
        return new ResponseEntity<>(new ResponseDto<>(
                true,
                postRepositoryImpl.findAllByCategory(category, area, pageable)
        ), HttpStatus.OK);
    }

    public ResponseEntity<ResponseDto<?>> readMyPosts(String filter, User user, int page, int size) {
        Pageable pageable = PageRequest.of(page,size);
        return new ResponseEntity<>(new ResponseDto<>(
                true,
                user.getNickname(),
                postRepositoryImpl.findAllByFilter(filter, user.getId(),pageable)
        ),HttpStatus.OK);
    }
}