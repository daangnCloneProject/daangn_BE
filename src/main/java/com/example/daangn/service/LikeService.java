package com.example.daangn.service;

import com.example.daangn.dto.ResponseDto;
import com.example.daangn.model.Like;
import com.example.daangn.model.Post;
import com.example.daangn.model.User;
import com.example.daangn.repository.like.LikeRepository;
import com.example.daangn.repository.like.LikeRepositoryImpl;
import com.example.daangn.repository.post.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LikeService {
    private final LikeRepository likeRepository;
    private final LikeRepositoryImpl likeRepositoryImpl;
    private final PostRepository postRepository;

    @Transactional
    public ResponseEntity<ResponseDto<?>> createLike(Long postId, User user) {
        Post post = postRepository.findById(postId)
                .orElseThrow(()->new IllegalArgumentException("해당 글이 존재하지 않습니다."));

        //본인이 작성한 글은 관심목록 등록 불가
        if(post.getUser().getId() == user.getId()){
            throw new IllegalArgumentException("본인 작성한 글은 관심목록 등록 불가합니다.");
        }

        //관심목록에 이미 등록했다면
        if (likeRepositoryImpl.findByPostIdAndUserId(postId, user.getId())!=null){
            throw new IllegalArgumentException("이미 관심목록에 추가된 글입니다.");
        }

        Like like = new Like(post,user);
        likeRepository.save(like);

        return new ResponseEntity<>(new ResponseDto<>(true,"관심목록에 추가성공"), HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity<ResponseDto<?>> deleteLike(Long postId, Long userId){
        Long likeId = likeRepositoryImpl.findByPostIdAndUserId(postId, userId);
        likeRepository.deleteById(likeId);
        return new ResponseEntity<>(new ResponseDto<>(true,"관심목록에서 삭제성공"), HttpStatus.OK);
    }
}
