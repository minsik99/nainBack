package io.paioneer.nain.statistical.model.service;


import io.paioneer.nain.statistical.jpa.repository.StatisticalRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import io.paioneer.nain.common.Span;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Transactional
@Service
@Slf4j
@RequiredArgsConstructor
public class StatisticalService {

    private final StatisticalRepository statisticalRepository;

    public long selectTotalPayAmount(Span span) {
        return statisticalRepository.totalAmountBetweenDates(span);
    }

    public long selectNewSubscribeCount(Span span) {
        return statisticalRepository.newSubscribeCountBetweenDates(span);
    }

//    public Long selectVisitorCount(Span span) {
//        return statisticalRepository.visitorCount(span);
//    }

    public Long selectSignupCount(Span span) {
        return statisticalRepository.signupCount(span);
    }

    public Long selectWithdrawalCount(Span span) {
        return statisticalRepository.withdrawalCount(span);
    }
}
