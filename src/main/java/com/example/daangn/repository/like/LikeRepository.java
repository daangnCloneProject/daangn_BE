package com.example.daangn.repository.like;

import com.example.daangn.model.Like;
import com.example.daangn.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LikeRepository extends JpaRepository<Like, Long> {
    List<Like> findAllByUser(User user);

    Long findByPostIdAndUserId(Long postId, Long userId);
}
