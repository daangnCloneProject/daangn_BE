package com.example.daangn.repository.post;

import com.example.daangn.dto.PostResultDto;
import com.example.daangn.model.AreaEnum;
import com.example.daangn.model.CategoryEnum;
import com.example.daangn.model.QLike;
import com.example.daangn.model.QPost;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class PostRepositoryImpl implements PostRepositoryCustom{
    private final JPAQueryFactory queryFactory;
    QPost post = QPost.post;
    QLike like = QLike.like;

    @Override
    public PostResultDto findByPostId(Long postId) {
        return queryFactory.select(Projections.fields(
                        PostResultDto.class,
                        post.id,
                        post.title,
                        post.category,
                        post.price,
                        post.area,
                        post.content,
                        post.imageUrl,
                        post.state,
                        post.user.id.as("userId"),
                        post.user.nickname,
                        post.createdAt.as("after"),
                        //좋아요수
                        ExpressionUtils.as(
                                JPAExpressions
                                        .select(like.count())
                                        .from(like)
                                        .where(like.post.id.eq(post.id)),"likeCount"
                        )
                ))
                .from(post)
                .where(post.id.eq(postId))
                .fetchOne();
    }

    @Override
    public Slice<PostResultDto> findAllByFilter(String filter, Long userId, Pageable pageable) {
        List<PostResultDto> returnPost;
        //join 부분도 동적으로 할수는 없나??
        if(filter.equals("sale")){
            returnPost = queryFactory.select(Projections.fields(
                            PostResultDto.class,
                            post.id,
                            post.title,
                            post.price,
                            post.area,
                            post.imageUrl,
                            post.state,
                            post.createdAt.as("after"),
                            //좋아요수
                            ExpressionUtils.as(
                                    JPAExpressions
                                            .select(like.count())
                                            .from(like)
                                            .where(like.post.id.eq(post.id)),"likeCount"
                            )
                    ))
                    .from(post)
                    .where(post.user.id.eq(userId))
                    .orderBy(post.createdAt.desc())
                    .offset(pageable.getOffset())
                    .limit(pageable.getPageSize())
                    .fetch();
        }else{
            returnPost = queryFactory.select(Projections.fields(
                            PostResultDto.class,
                            post.id,
                            post.title,
                            post.price,
                            post.area,
                            post.imageUrl,
                            post.state,
                            post.createdAt.as("after"),
                            //좋아요수
                            ExpressionUtils.as(
                                    JPAExpressions
                                            .select(like.count())
                                            .from(like)
                                            .where(like.post.id.eq(post.id)),"likeCount"
                            )
                    ))
                    .from(post)
                    .join(post.likeList,like)
                    .where(like.user.id.eq(userId))
                    .orderBy(post.createdAt.desc())
                    .offset(pageable.getOffset())
                    .limit(pageable.getPageSize())
                    .fetch();
        }

        return new SliceImpl<>(returnPost, pageable, returnPost.iterator().hasNext());
    }

    @Override
    public Slice<PostResultDto> findAllByCategory(String category, String area, Pageable pageable) {
        List<PostResultDto> returnPost = queryFactory.select(Projections.fields(
                        PostResultDto.class,
                        post.id,
                        post.title,
                        post.price,
                        post.area,
                        post.imageUrl,
                        post.state,
                        post.createdAt.as("after"),
                        //좋아요수
                        ExpressionUtils.as(
                                JPAExpressions
                                        .select(like.count())
                                        .from(like)
                                        .where(like.post.id.eq(post.id)),"likeCount"
                        )
                ))
                .from(post)
                .where(categoryEq(category),areaEq(area))
                .orderBy(post.createdAt.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
        return new SliceImpl<>(returnPost, pageable, returnPost.iterator().hasNext());
    }

    @Override
    public Slice<PostResultDto> findAllByKeyword(String titlekeyword, String contentkeyword, Pageable pageable) {
        List<PostResultDto> returnPost = queryFactory.select(Projections.fields(
                        PostResultDto.class,
                        post.id,
                        post.title,
                        post.price,
                        post.area,
                        post.imageUrl,
                        post.state,
                        post.createdAt.as("after"),
                        //좋아요수
                        ExpressionUtils.as(
                                JPAExpressions
                                        .select(like.count())
                                        .from(like)
                                        .where(like.post.id.eq(post.id)),"likeCount"
                        )
                ))
                .from(post)
                .where(post.title.contains(titlekeyword).or(post.content.contains(contentkeyword)))
                .orderBy(post.createdAt.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
        return new SliceImpl<>(returnPost, pageable, returnPost.iterator().hasNext());
    }

    @Override
    public Long findOneByUsername(String username) {
        return queryFactory.select(post.id)
                .from(post)
                .where(post.user.username.eq(username))
                .fetchFirst();
    }

    private BooleanExpression categoryEq(String category) {
        return category.equals("ALL") ? null : post.category.eq(CategoryEnum.valueOf(category));
    }

    private BooleanExpression areaEq(String area) {
        return area.equals("ALL") ? null : post.area.eq(AreaEnum.valueOf(area));
    }

    private BooleanExpression postUserIdEq(String filter, Long userId) {
        return filter.equals("sale")? post.user.id.eq(userId) : null;
    }

    private BooleanExpression likeUserIdEq(String filter, Long userId) {
        return filter.equals("interest")? like.user.id.eq(userId) : null;
    }

}
