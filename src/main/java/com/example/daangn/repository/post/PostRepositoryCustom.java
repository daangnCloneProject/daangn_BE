package com.example.daangn.repository.post;

import com.example.daangn.dto.PostResultDto;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface PostRepositoryCustom {
    Slice<PostResultDto> findAllByFilter(String filter, Long userId, Pageable pageable);

    Slice<PostResultDto> findAllByCategory(String category, String area, Pageable pageable);

    PostResultDto findByPostId(Long postId);
}
