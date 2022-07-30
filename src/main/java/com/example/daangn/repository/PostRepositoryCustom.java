package com.example.daangn.repository;

import com.example.daangn.dto.PostResultDto;

public interface PostRepositoryCustom {
    PostResultDto findByPostId(Long postId);
    PostResultDto findByFilter(String filter);
}
