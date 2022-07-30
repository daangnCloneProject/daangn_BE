package com.example.daangn.repository;

import com.example.daangn.dto.PostResultDto;
import com.example.daangn.dto.ResponseDto;
import com.example.daangn.model.QLike;
import com.example.daangn.model.QPost;
import com.example.daangn.model.QUser;
import com.querydsl.core.NonUniqueResultException;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class PostRepositoryImpl implements PostRepositoryCustom{
    private final JPAQueryFactory queryFactory;

    QPost post = QPost.post;
    QLike like = QLike.like;
    QUser user = QUser.user;

    @Override
    public List<PostResultDto> findAllByFilter(String filter, Long userId) {
        return queryFactory.select(Projections.fields(
                        PostResultDto.class,
                        post.title,
                        post.category,
                        post.price,
                        post.area,
                        post.content,
                        post.imageUrl,
                        post.createdAt,
                        post.user.id
                ))
                .from(post)
                .where(whereByFilter(filter,userId))
//                .offset(pageable.getOffset())
//                .limit(pageable.getPageSize())
                .fetch();
    }

    private BooleanExpression whereByFilter(String filter, Long userId) {
        return filter.equals("sale")? post.user.id.eq(userId) : like.post.user.id.eq(userId);
    }
}
