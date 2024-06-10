package io.paioneer.nain.statistical.model.service;


import io.paioneer.nain.statistical.jpa.repository.StatisticalRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import io.paioneer.nain.common.Span;

import java.time.LocalDate;

@Service
@Slf4j
public class StatisticalService {

    private final StatisticalRepository statisticalRepository;

    public StatisticalService(StatisticalRepository statisticalRepository) {
        this.statisticalRepository = statisticalRepository;
    }

    private long convertToLong(long value) {
        return (long) value;
    }

    public long selectTotalPayAmount(Span span) {
        return convertToLong(statisticalRepository.totalAmountBetweenDates(span));
    }

    public long selectNewSubscribeCount(Span span) {
        return convertToLong(statisticalRepository.newSubscribeCountBetweenDates(span));
    }

//    public Long selectVisitorCount(Span span) {
//        return convertToLong(statisticalRepository.visitorCount(span));
//    }

    public Long selectSignupCount(Span span) {
        return convertToLong(statisticalRepository.signupCount(span));
    }

    public Long selectWithdrawalCount(Span span) {
        return convertToLong(statisticalRepository.withdrawalCount(span));
    }
}
