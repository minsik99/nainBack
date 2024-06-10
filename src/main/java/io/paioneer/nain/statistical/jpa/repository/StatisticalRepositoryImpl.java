package io.paioneer.nain.statistical.jpa.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import io.paioneer.nain.common.Span;
import io.paioneer.nain.subscribe.jpa.entity.QSubscribeEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import java.sql.Date;

import java.time.LocalDate;

@Repository
@RequiredArgsConstructor
public class StatisticalRepositoryImpl implements StatisticalRepositoryCustom {

    @PersistenceContext
    private final JPAQueryFactory queryFactory;
    private final EntityManager entityManager;

    private QSubscribeEntity subscribeEntity = QSubscribeEntity.subscribeEntity;

    @Override
    public double totalAmountBetweenDates(Span span) {
        return queryFactory
                .select(subscribeEntity.payAmount.sum())
                .from(subscribeEntity)
                .where(subscribeEntity.paymentDate.between(Date.valueOf(span.getBegin()),Date.valueOf(span.getEnd())))
                .fetchOne();
    }
}
