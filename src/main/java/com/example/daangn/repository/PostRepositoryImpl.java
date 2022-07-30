package com.example.daangn.repository;

import com.example.daangn.dto.PostResultDto;
import com.example.daangn.model.CategoryEnum;
import com.example.daangn.model.QPost;
import com.example.daangn.model.QUser;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class PostRepositoryImpl implements PostRepositoryCustom{
    private final JPAQueryFactory queryFactory;

    QPost post = QPost.post;
    QUser user = QUser.user;

    @Override
    public PostResultDto findByPostId(Long postId) {
        return (PostResultDto) queryFactory.select(Projections.fields(
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
                .where(post.user.id.eq(postId))
                .fetch();
    }

    @Override
    public List<PostResultDto> findAllByFilterOrUserId(String filter, Long userId) {
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
                .where(post.user.id.eq(userId)) // 판매글
                .where(categoryContains(filter))
                .fetch();
    }

    private BooleanExpression categoryContains(String category) {
        return category.equals("ALL") ? null : post.category.eq(CategoryEnum.valueOf(category));
    }
}
