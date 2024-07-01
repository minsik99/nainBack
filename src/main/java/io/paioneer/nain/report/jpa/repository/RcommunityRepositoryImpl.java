package io.paioneer.nain.report.jpa.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import io.paioneer.nain.report.jpa.entity.QRcommentEntity;
import io.paioneer.nain.report.jpa.entity.QRcommunityEntity;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RcommunityRepositoryImpl implements RcommunityRepositoryCustom {
    private final JPAQueryFactory queryFactory;
    private QRcommunityEntity rcommunityEntity =  QRcommunityEntity.rcommunityEntity;

    @Override
    public int reportHistoryCount(Long memberNo, Long communityNo) {
        return Math.toIntExact(queryFactory
                .select(rcommunityEntity.count())
                .from(rcommunityEntity)
                .where(rcommunityEntity.memberEntity.memberNo.eq(memberNo)
                        .and(rcommunityEntity.communityEntity.communityNo.eq(communityNo)))
                .fetchOne());
    }

}
