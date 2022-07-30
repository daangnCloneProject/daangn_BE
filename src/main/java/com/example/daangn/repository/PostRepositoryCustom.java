package com.example.daangn.repository;

import com.example.daangn.dto.PostResultDto;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.List;

public interface PostRepositoryCustom {
    Slice<PostResultDto> findAllByFilter(String filter, Long userId, Pageable pageable);

    Slice<PostResultDto> findAllByCategory(String category, Pageable pageable);

    PostResultDto findByPostId(Long postId);
}
