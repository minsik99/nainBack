package io.paioneer.nain.report.jpa.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import io.paioneer.nain.report.jpa.entity.QRcommentEntity;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RcommentRepositoryImpl implements  RcommentRepositoryCustom{
    private final JPAQueryFactory queryFactory;
    private QRcommentEntity rcommentEntity = QRcommentEntity.rcommentEntity;

    @Override
    public int reportCommentHistoryCount(Long memberNo, Long commentNo) {
        return Math.toIntExact(queryFactory
                .select(rcommentEntity.count())
                .from(rcommentEntity)
                .where(rcommentEntity.memberEntity.memberNo.eq(memberNo)
                        .and(rcommentEntity.commentEntity.commentNo.eq(commentNo)))
                .fetchOne());
    }
}
