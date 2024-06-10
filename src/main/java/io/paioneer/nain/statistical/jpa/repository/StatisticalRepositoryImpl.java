package io.paioneer.nain.statistical.jpa.repository;

import com.querydsl.core.types.Expression;
import com.querydsl.core.types.dsl.NumberExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import io.paioneer.nain.common.Span;
import io.paioneer.nain.member.jpa.entity.MemberEntity;
import io.paioneer.nain.member.jpa.entity.QMemberEntity;
import io.paioneer.nain.subscribe.jpa.entity.QSubscribeEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import java.sql.Date;
import java.time.LocalDateTime;

@Repository
@RequiredArgsConstructor
public class StatisticalRepositoryImpl implements StatisticalRepositoryCustom {

    @PersistenceContext

    private final JPAQueryFactory queryFactory;
    private final QSubscribeEntity subscribeEntity = QSubscribeEntity.subscribeEntity;
    private final QMemberEntity memberEntity = QMemberEntity.memberEntity;


    @Override
    public long totalAmountBetweenDates(Span span) {
        return queryFactory
                .select(subscribeEntity.payAmount.sum())
                .from(subscribeEntity)
                .where(subscribeEntity.paymentDate.between(Date.valueOf(span.getBegin()),Date.valueOf(span.getEnd())))
                .fetchOne();
    }


    @Override
    public long newSubscribeCountBetweenDates(Span span) {
        return queryFactory
                .select(subscribeEntity.payNo.count())
                .from(subscribeEntity)
                .where(subscribeEntity.paymentDate.between(Date.valueOf(span.getBegin()),Date.valueOf(span.getEnd())))
                .fetchOne();
    }

//    @Override
//    public long visitorCount(Span span) {
//        return queryFactory
//                .select();
//    }

    @Override
    public long signupCount(Span span) {
        return queryFactory
                .select(memberEntity.memberNo.count())
                .from(memberEntity)
                .where(memberEntity.signUpDate.between((Expression<LocalDateTime>) Date.valueOf(span.getBegin()), (Expression<LocalDateTime>) Date.valueOf(span.getEnd())))
                .fetchOne();
    }

    @Override
    public long withdrawalCount(Span span) {
        return queryFactory
                .select(memberEntity.memberNo.count())
                .from(memberEntity)
                .where(memberEntity.withDrawalDate.between((Expression<LocalDateTime>) Date.valueOf(span.getBegin()), (Expression<LocalDateTime>) Date.valueOf(span.getEnd())))
                .fetchOne();
    }
}
