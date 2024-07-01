package io.paioneer.nain.report.jpa.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import io.paioneer.nain.community.jpa.entity.QCommentEntity;
import io.paioneer.nain.member.jpa.entity.QMemberEntity;
import io.paioneer.nain.report.jpa.entity.QRcommentEntity;
import io.paioneer.nain.report.model.dto.CommentReportCountDto;
import io.paioneer.nain.report.model.dto.CommentReportDto;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class CommentReportRepositoryImpl implements CommentReportRepositoryCustom{

    private final JPAQueryFactory queryFactory;
    QRcommentEntity commentRepositoryEntity = QRcommentEntity.rcommentEntity;
    QCommentEntity commentEntity = QCommentEntity.commentEntity;
    QMemberEntity memberEntity = QMemberEntity.memberEntity;
    QMemberEntity adminMember = new QMemberEntity("adminMember");

    @Override
    public List<CommentReportDto> getCommentReport() {
        return queryFactory
                .select(Projections.constructor(CommentReportDto.class,
                        commentRepositoryEntity.cReportNo,
                        commentRepositoryEntity.reportDate,
                        commentEntity.commentNo,
                        memberEntity.memberEmail,
                        memberEntity.memberName,
                        commentRepositoryEntity.handledYN,
                        commentEntity.content,
                        commentRepositoryEntity.reportType,
                        commentRepositoryEntity.handledDate,
                        adminMember.memberName
                ))
                .from(commentRepositoryEntity)
                .leftJoin(commentEntity).on(commentRepositoryEntity.commentEntity.commentNo.eq(commentEntity.commentNo))
                .leftJoin(memberEntity).on(commentEntity.memberEntity.memberNo.eq(memberEntity.memberNo))
                .leftJoin(adminMember).on(commentRepositoryEntity.adminEntity.memberNo.eq(adminMember.memberNo))
                .fetch();
    }

    @Override
    public List<CommentReportCountDto> getCommentReportCount() {
        return queryFactory
                .select(Projections.constructor(CommentReportCountDto.class,
                        commentRepositoryEntity.commentEntity.commentNo,
                        commentRepositoryEntity.reportType,
                        commentRepositoryEntity.count()
                ))
                .from(commentRepositoryEntity)
                .groupBy(commentRepositoryEntity.commentEntity.commentNo, commentRepositoryEntity.reportType)
                .fetch();
    }
}
