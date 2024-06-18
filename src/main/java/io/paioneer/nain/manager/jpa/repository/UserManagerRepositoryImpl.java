package io.paioneer.nain.manager.jpa.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import io.paioneer.nain.blockMember.entity.QBlockMemberEntity;
import io.paioneer.nain.manager.model.dto.AdminListDto;
import io.paioneer.nain.manager.model.dto.UserListDto;
import io.paioneer.nain.member.jpa.entity.QMemberEntity;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class UserManagerRepositoryImpl implements UserManagerRepositoryCustom{

    private final JPAQueryFactory queryFactory;
    QMemberEntity memberEntity = QMemberEntity.memberEntity;
    QBlockMemberEntity blockMemberEntity = QBlockMemberEntity.blockMemberEntity;

    @Override
    public List<UserListDto> userList() {

        return queryFactory
                .select(Projections.constructor(UserListDto.class,
                        memberEntity.memberEmail,
                        memberEntity.memberName,
                        memberEntity.memberNickName,
                        memberEntity.subscribeYN,
                        blockMemberEntity.blockYN,
                        blockMemberEntity.blockComment
                ))
                .from(memberEntity)
                .leftJoin(blockMemberEntity).on(memberEntity.memberNo.eq(blockMemberEntity.memberNo2.memberNo))
                .where(memberEntity.admin.eq(false))
                .fetch();
    }


}
