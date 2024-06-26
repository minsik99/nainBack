package io.paioneer.nain.manager.jpa.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import io.paioneer.nain.manager.model.dto.AdminListDto;
import io.paioneer.nain.member.jpa.entity.QMemberEntity;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class AdminManagerRepositoryImpl implements AdminManagerRepositoryCustom {

    private final JPAQueryFactory queryFactory;
    QMemberEntity memberEntity = QMemberEntity.memberEntity;

    @Override
    public List<AdminListDto> adminList() {
        return queryFactory
                .select(Projections.constructor(AdminListDto.class,memberEntity.memberEmail,
                        memberEntity.memberName,
                        memberEntity.memberNickName))
                .from(memberEntity)
                .where(memberEntity.admin.eq(true))
                .fetch();
    }
}
