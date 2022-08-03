package com.example.daangn.repository.like;

import com.example.daangn.model.Like;

public interface LikeRepositoryCustom {
    Long findByPostIdAndUserId(Long postId, Long userId);

    Like findOneByUsername(String username);
}
