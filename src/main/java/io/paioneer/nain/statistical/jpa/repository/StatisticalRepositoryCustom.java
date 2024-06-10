package io.paioneer.nain.statistical.jpa.repository;

import io.paioneer.nain.common.Span;

import java.time.LocalDate;

public interface StatisticalRepositoryCustom {

    long totalAmountBetweenDates(Span span);

    long newSubscribeCountBetweenDates(Span span);

//    long visitorCount(Span span);

    long signupCount(Span span);

    long withdrawalCount(Span span);
}
