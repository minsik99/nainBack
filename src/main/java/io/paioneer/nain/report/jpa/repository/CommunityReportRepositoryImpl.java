package io.paioneer.nain.report.jpa.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import io.paioneer.nain.community.jpa.entity.QCommunityEntity;
import io.paioneer.nain.member.jpa.entity.QMemberEntity;
import io.paioneer.nain.report.jpa.entity.QRcommunityEntity;
import io.paioneer.nain.report.model.dto.CommunityReportCountDto;
import io.paioneer.nain.report.model.dto.CommunityReportDto;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class CommunityReportRepositoryImpl implements CommunityReportRepositoryCustom {

    private final JPAQueryFactory queryFactory;
    QRcommunityEntity communityReportEntity = QRcommunityEntity.rcommunityEntity;
    QCommunityEntity communityEntity = QCommunityEntity.communityEntity;
    QMemberEntity memberEntity = QMemberEntity.memberEntity;

    @Override
    public List<CommunityReportCountDto> getCommunityReportCount() {

        return queryFactory
                .select(Projections.constructor(CommunityReportCountDto.class,
                        communityReportEntity.communityEntity.communityNo,
                        communityReportEntity.reportType,
                        communityReportEntity.count()
                ))
                .from(communityReportEntity)
                .groupBy(communityReportEntity.communityEntity.communityNo, communityReportEntity.reportType)
                .fetch();
    }

    @Override
    public List<CommunityReportDto> getCommunityReport() {
        return queryFactory
                .select(Projections.constructor(CommunityReportDto.class,
                        communityReportEntity.bReportNo,
                        communityReportEntity.reportDate,
                        memberEntity.memberEmail,
                        memberEntity.memberName,
                        communityReportEntity.handledYN,
                        communityEntity.title,
                        communityEntity.content,
                        communityReportEntity.reportType,
                        communityReportEntity.handledDate,
                        memberEntity.memberName,
                        communityEntity.communityNo
                ))
                .from(communityReportEntity)
                .leftJoin(communityEntity).on(communityReportEntity.communityEntity.communityNo.eq(communityEntity.communityNo))
                .leftJoin(memberEntity).on(communityEntity.memberEntity.memberNo.eq(memberEntity.memberNo))
                .orderBy(communityReportEntity.communityEntity.communityNo.asc(), communityReportEntity.reportDate.desc())
                .fetch();
    }
}
