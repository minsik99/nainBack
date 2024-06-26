package io.paioneer.nain.member.jpa.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import io.paioneer.nain.member.jpa.entity.MemberEntity;
import io.paioneer.nain.member.jpa.entity.QMemberEntity;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Repository
@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepositoryCustom{
    private final JPAQueryFactory jpaQueryFactory;
    private final EntityManager entityManager;
    private QMemberEntity memberEntity = QMemberEntity.memberEntity;


    @Override
    public long emailCount(String memberEmail) {
        return jpaQueryFactory
                .select(memberEntity.count())
                .from(memberEntity)
                .where(memberEntity.memberEmail.eq(memberEmail))
                .fetchOne();
//        log.info(String.valueOf(count));

    }
}
