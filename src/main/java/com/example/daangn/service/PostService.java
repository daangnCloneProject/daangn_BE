package com.example.daangn.service;

import com.example.daangn.dto.PostRequestDto;
import com.example.daangn.dto.PostResultDto;
import com.example.daangn.dto.ResponseDto;
import com.example.daangn.model.Post;
import com.example.daangn.model.User;
import com.example.daangn.repository.LikeRepository;
import com.example.daangn.repository.PostRepository;
import com.example.daangn.repository.PostRepositoryImpl;
import com.example.daangn.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

@Service
@Transactional
public class PostService {
    private final PostRepository postRepository;
    private final PostRepositoryImpl postRepositoryImpl;
    private final UserRepository userRepository;
    private final LikeRepository likeRepository;

    @Autowired
    public PostService(PostRepository postRepository, PostRepositoryImpl postRepositoryImpl, UserRepository userRepository , LikeRepository likeRepository) {
        this.postRepository = postRepository;
        this.postRepositoryImpl = postRepositoryImpl;
        this.userRepository = userRepository;
        this.likeRepository = likeRepository;
    }



    public ResponseEntity<ResponseDto<?>> createPost(PostRequestDto requestDto, User user) {
        Post post = new Post(requestDto, user);
        postRepository.save(post);
        return new ResponseEntity<>(new ResponseDto<>(true, "게시글 생성 성공"), HttpStatus.OK);
    }


    public ResponseEntity<ResponseDto<?>> readPost(Long postId) {
        PostResultDto post = postRepositoryImpl.findByPostId(postId);

        return new ResponseEntity<>(new ResponseDto<>(true, "게시글 생성 성공", post), HttpStatus.OK);
    }


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

    public ResponseEntity<ResponseDto<?>> deletePost(Long postId, User user) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 포스트입니다."));
        if(!user.getId().equals(post.getUser().getId())){
            throw new IllegalArgumentException("접근 권한이 없는 사용자입니다.");
        }
        postRepository.deleteById(postId);
        return new ResponseEntity<>(new ResponseDto<>(true, "게시글 삭제 성공"), HttpStatus.OK);
    }

    public ResponseEntity<ResponseDto<?>> readMyPosts(String filter, User user) {
        return new ResponseEntity<>(new ResponseDto<>(
                true,
                user.getNickname(),
                postRepositoryImpl.findAllByFilter(filter, user.getId())
        ),HttpStatus.OK);
    }
}