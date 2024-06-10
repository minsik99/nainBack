package io.paioneer.nain.statistical.jpa.repository;

import io.paioneer.nain.common.Span;

import java.time.LocalDate;

public interface StatisticalRepositoryCustom {

    double totalAmountBetweenDates(Span span);
}
