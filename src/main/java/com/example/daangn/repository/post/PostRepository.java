package com.example.daangn.repository.post;

import com.example.daangn.dto.PostResultDto;
import com.example.daangn.model.CategoryEnum;
import com.example.daangn.model.Post;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
}

