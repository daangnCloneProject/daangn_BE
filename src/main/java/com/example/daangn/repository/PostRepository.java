package com.example.daangn.repository;

import com.example.daangn.model.Post;
import com.example.daangn.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllByStateAndUserAndOrderByCreatedAt(String filter, User user);
}

