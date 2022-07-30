package com.example.daangn.repository;

import com.example.daangn.dto.PostResultDto;
import java.util.List;

public interface PostRepositoryCustom {
    List<PostResultDto> findAllByFilter(String filter, Long userId);
}
