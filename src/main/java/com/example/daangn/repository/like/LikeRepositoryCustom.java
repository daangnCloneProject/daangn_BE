package com.example.daangn.repository.like;

public interface LikeRepositoryCustom {
    Long findByPostIdAndUserId(Long postId, Long userId);
}
