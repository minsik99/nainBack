package io.paioneer.nain.report.jpa.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import io.paioneer.nain.community.jpa.entity.QCommunityEntity;
import io.paioneer.nain.member.jpa.entity.QMemberEntity;
import io.paioneer.nain.report.jpa.entity.QRcommunityEntity;
import io.paioneer.nain.report.model.dto.CommunityReportDto;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class CommunityReportRepositoryImpl implements CommunityRepositoryCustom{

    private final JPAQueryFactory queryFactory;
    QRcommunityEntity communityReportEntity = QRcommunityEntity.rcommunityEntity;
    QCommunityEntity communityEntity = QCommunityEntity.communityEntity;
    QMemberEntity memberEntity = QMemberEntity.memberEntity;

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
                        memberEntity.memberName
                ))
                .from(communityReportEntity)
                .leftJoin(communityEntity).on(communityReportEntity.communityEntity.communityNo.eq(communityEntity.communityNo))
                .leftJoin(memberEntity).on(communityEntity.memberEntity.memberNo.eq(memberEntity.memberNo))
                .fetch();
    }

}
