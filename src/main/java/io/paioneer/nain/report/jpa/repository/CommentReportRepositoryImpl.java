package io.paioneer.nain.report.jpa.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import io.paioneer.nain.community.jpa.entity.QCommentEntity;
import io.paioneer.nain.member.jpa.entity.QMemberEntity;
import io.paioneer.nain.report.jpa.entity.QRcommentEntity;
import io.paioneer.nain.report.model.dto.CommentReportDto;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class CommentReportRepositoryImpl implements CommentReportRepositoryCustom{

    private final JPAQueryFactory queryFactory;
    QRcommentEntity commentRepositoryEntity = QRcommentEntity.rcommentEntity;
    QCommentEntity commentEntity = QCommentEntity.commentEntity;
    QMemberEntity memberEntity = QMemberEntity.memberEntity;

    @Override
    public List<CommentReportDto> getCommentReport() {
        return queryFactory
                .select(Projections.constructor(CommentReportDto.class,
                        commentRepositoryEntity.cReportNo,
                        commentRepositoryEntity.reportDate,
                        memberEntity.memberEmail,
                        memberEntity.memberName,
                        commentRepositoryEntity.handledYN,
                        commentEntity.content,
                        commentRepositoryEntity.reportType,
                        commentRepositoryEntity.handledDate,
                        memberEntity.memberName
                ))
                .from(commentRepositoryEntity)
                .leftJoin(commentEntity).on(commentRepositoryEntity.commentEntity.commentNo.eq(commentEntity.commentNo))
                .leftJoin(memberEntity).on(commentEntity.memberEntity.memberNo.eq(memberEntity.memberNo))
                .fetch();
    }
}
