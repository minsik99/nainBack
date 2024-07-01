package io.paioneer.nain.report.jpa.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import io.paioneer.nain.community.jpa.entity.QCommunityEntity;
import io.paioneer.nain.member.jpa.entity.QMemberEntity;
import io.paioneer.nain.report.jpa.entity.QRcommunityEntity;
import io.paioneer.nain.report.model.dto.CommunityReportCountDto;
import io.paioneer.nain.report.model.dto.CommunityReportDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class CommunityReportRepositoryImpl implements CommunityReportRepositoryCustom {

    private final JPAQueryFactory queryFactory;
    QRcommunityEntity communityReportEntity = QRcommunityEntity.rcommunityEntity;
    QCommunityEntity communityEntity = QCommunityEntity.communityEntity;
    QMemberEntity memberEntity = QMemberEntity.memberEntity;
    QMemberEntity adminEntity = new QMemberEntity("adminEntity");

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
                        communityEntity.communityNo,
                        memberEntity.memberEmail,
                        memberEntity.memberName,
                        communityReportEntity.handledYN,
                        communityEntity.title,
                        communityEntity.content,
                        communityReportEntity.reportType,
                        communityReportEntity.handledDate,
                        adminEntity.memberName,
                        memberEntity.memberNo
                ))
                .from(communityReportEntity)
                .leftJoin(communityEntity).on(communityReportEntity.communityEntity.communityNo.eq(communityEntity.communityNo))
                .leftJoin(memberEntity).on(communityEntity.memberEntity.memberNo.eq(memberEntity.memberNo))
                .leftJoin(adminEntity).on(communityReportEntity.adminEntity.memberNo.eq(adminEntity.memberNo)) // 조인 추가
                .orderBy(communityReportEntity.communityEntity.communityNo.asc(), communityReportEntity.reportDate.desc())
                .fetch();
    }

}
