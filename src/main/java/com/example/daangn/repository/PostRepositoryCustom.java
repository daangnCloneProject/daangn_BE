package com.example.daangn.repository;

import com.example.daangn.dto.PostResultDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PostRepositoryCustom {
    PostResultDto findByPostId(Long postId);
    List<PostResultDto> findAllByFilter(String filter, Long userId, Pageable pageable);
}
