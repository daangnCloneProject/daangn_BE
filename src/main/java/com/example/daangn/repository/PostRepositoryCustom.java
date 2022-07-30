package com.example.daangn.repository;

import com.example.daangn.dto.PostResultDto;

import java.util.List;

public interface PostRepositoryCustom {
    PostResultDto findByPostId(Long postId);
    List<PostResultDto> findAllByFilterOrUserId(String filter, Long userId);
}
