package com.example.daangn.repository;

import com.example.daangn.dto.PostResultDto;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;


public interface PostRepositoryCustom {
    PostResultDto findByPostId(Long postId);
}
