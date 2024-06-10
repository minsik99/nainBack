package io.paioneer.nain.community.jpa.repository.comment;

import com.querydsl.jpa.impl.JPAQueryFactory;
import io.paioneer.nain.community.jpa.entity.CommentEntity;
import io.paioneer.nain.community.jpa.entity.QCommentEntity;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CommentRepositoryImpl implements CommentRepositoryCustom {
    private final JPAQueryFactory queryFactory;
    private final EntityManager entityManger;
    private QCommentEntity commentEntity = QCommentEntity.commentEntity1;

    //삭제 상태가 아닌 댓글 목록 조회
    @Override
    public List<CommentEntity> findList(Long communityNo) {
        return queryFactory
                .selectFrom(commentEntity)
                .where(commentEntity.communityEntity.communityNo.eq(communityNo)
                .and(commentEntity.deletedDate.eq(null))
                .orderBy(commentEntity.commentDate.desc())
                .fetch();
    }

}