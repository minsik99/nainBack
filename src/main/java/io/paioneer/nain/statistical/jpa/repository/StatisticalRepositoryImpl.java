package io.paioneer.nain.statistical.jpa.repository;

import com.querydsl.core.types.dsl.DateTimePath;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import io.paioneer.nain.common.Span;
import io.paioneer.nain.common.SpanTime;
import io.paioneer.nain.member.jpa.entity.QMemberEntity;
import io.paioneer.nain.subscribe.jpa.entity.QSubscribeEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.sql.Date;
import java.time.LocalDateTime;

@Slf4j
@RequiredArgsConstructor
public class StatisticalRepositoryImpl implements StatisticalRepositoryCustom {


    private final JPAQueryFactory queryFactory;
    private final QSubscribeEntity subscribeEntity = QSubscribeEntity.subscribeEntity;
    private final QMemberEntity memberEntity = QMemberEntity.memberEntity;

    //연 매출 통계
    @Override
    public Long selectYearlyTotalPayAmount(Span span) {
        Integer i = queryFactory
                .select(subscribeEntity.payAmount.sum())
                .from(subscribeEntity)
                .where(subscribeEntity.paymentDate.between(Date.valueOf(span.getBegin()), Date.valueOf(span.getEnd())))
                .fetchOne();

        return i == null ? 0 : Long.valueOf(i);
    }

    //월 매출 통계
    @Override
    public Long selectMonthlyTotalPayAmount(Span span) {
        Integer i = queryFactory
                .select(subscribeEntity.payAmount.sum())
                .from(subscribeEntity)
                .where(subscribeEntity.paymentDate.between(Date.valueOf(span.getBegin()), Date.valueOf(span.getEnd())))
                .fetchOne();

        return i == null ? 0 : Long.valueOf(i);
    }

    //전체 회원수 통계
    @Override
    public int selectMemberCount() {
        Long memberCount = queryFactory
                .select(memberEntity.memberNo.count())
                .from(memberEntity)
                .where(memberEntity.withDrawalDate.isNull())
                .fetchOne();

        return Math.toIntExact(memberCount != null ? memberCount : 0);
    }

    //전체 구독자수 통계
    @Override
    public int selectSubscription() {
        Long SubscriptionCount = queryFactory
                .select(memberEntity.memberNo.count())
                .from(memberEntity)
                .where(memberEntity.subscribeYN.eq(String.valueOf('Y')))
                .fetchOne();

        return Math.toIntExact(SubscriptionCount != null ? SubscriptionCount : 0);
    }

    //기간별 신규 구독자 통계
    @Override
    public int countNewSubscribersInSpan(SpanTime span) {

        DateTimePath<LocalDateTime> subscriptionDateTime = Expressions.dateTimePath(LocalDateTime.class, "paymentDate");

        Long count = queryFactory
                .select(subscribeEntity.count())
                .from(subscribeEntity)
                .where(subscriptionDateTime.between(span.getBegin(), span.getEnd()))
                .fetchOne();

        return count != null ? count.intValue() : 0;
    }

    //기간별 신규 가입자 통계
    @Override
    public int countNewMembers(SpanTime span) {

        DateTimePath<LocalDateTime> signUpDateTime = Expressions.dateTimePath(LocalDateTime.class, "signUpDate");

        Long count = queryFactory
                .select(memberEntity.count())
                .from(memberEntity)
                .where(signUpDateTime.between(span.getBegin(), span.getEnd()))
                .fetchOne();

        return count != null ? count.intValue() : 0;

    }

    //탈퇴자 누적 통계
    @Override
    public int withdrawal(SpanTime span) {
        DateTimePath<LocalDateTime> signUpDateTime = Expressions.dateTimePath(LocalDateTime.class, "withDrawalDate");

        Long count = queryFactory
                .select(memberEntity.count())
                .from(memberEntity)
                .where(signUpDateTime.between(span.getBegin(), span.getEnd()))
                .fetchOne();

        return count != null ? count.intValue() : 0;
    }
}
