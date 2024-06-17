package io.paioneer.nain.statistical.jpa.repository;

import io.paioneer.nain.common.Span;
import io.paioneer.nain.common.SpanTime;

import java.time.LocalDate;

public interface StatisticalRepositoryCustom {

    Long selectYearlyTotalPayAmount(Span span);

    Long selectMonthlyTotalPayAmount(Span span);

    int countNewSubscribersInSpan(SpanTime span);

    int selectMemberCount();

    int selectSubscription();

    int countNewMembers(SpanTime span);

    int withdrawal(SpanTime span);
}
