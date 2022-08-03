package com.example.daangn.repository.like;

import com.example.daangn.model.Like;
import com.example.daangn.model.QLike;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class LikeRepositoryImpl implements LikeRepositoryCustom{
    private final JPAQueryFactory queryFactory;

    QLike like = QLike.like;

    @Override
    public Long findByPostIdAndUserId(Long postId, Long userId) {
        return queryFactory.select(like.id)
                .from(like)
                .where(like.post.id.eq(postId),like.user.id.eq(userId))
                .fetchOne();
    }

    @Override
    public Like findOneByUsername(String username) {
        return queryFactory.selectFrom(like)
                .where(like.user.username.eq(username))
                .fetchOne();
    }
}
