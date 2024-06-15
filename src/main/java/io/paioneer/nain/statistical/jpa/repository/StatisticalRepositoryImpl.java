package io.paioneer.nain.statistical.jpa.repository;

import com.querydsl.core.types.Expression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import io.paioneer.nain.common.Span;
import io.paioneer.nain.member.jpa.entity.QMemberEntity;
import io.paioneer.nain.subscribe.jpa.entity.QSubscribeEntity;
import lombok.RequiredArgsConstructor;
import java.sql.Date;
import java.time.LocalDateTime;

@RequiredArgsConstructor
public class StatisticalRepositoryImpl implements StatisticalRepositoryCustom {


    private final JPAQueryFactory queryFactory;
    private final QSubscribeEntity subscribeEntity = QSubscribeEntity.subscribeEntity;
    private final QMemberEntity memberEntity = QMemberEntity.memberEntity;

    @Override
    public Long selectYearTotalPayAmount(Span span) {
        Integer i = queryFactory
                .select(subscribeEntity.payAmount.sum())
                .from(subscribeEntity)
                .where(subscribeEntity.paymentDate.between(Date.valueOf(span.getBegin()), Date.valueOf(span.getEnd())))
                .fetchOne();
        if (i == null) {
            i=0;
        }
        return Long.valueOf(i);
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
