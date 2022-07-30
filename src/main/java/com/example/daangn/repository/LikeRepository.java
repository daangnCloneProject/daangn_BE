package com.example.daangn.repository;

import com.example.daangn.model.Like;
import com.example.daangn.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LikeRepository extends JpaRepository<Like, Long> {
    List<Like> findAllByUser(User user);
}
