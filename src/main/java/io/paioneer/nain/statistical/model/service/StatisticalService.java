package io.paioneer.nain.statistical.model.service;


import io.paioneer.nain.statistical.jpa.repository.StatisticalRepository;
import io.paioneer.nain.subscribe.model.dto.YearlySubscribePaymentDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import io.paioneer.nain.common.Span;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
@Slf4j
@RequiredArgsConstructor
public class StatisticalService {

    private final StatisticalRepository statisticalRepository;

    public List<YearlySubscribePaymentDto> selectYearTotalPayAmount() {
        int now = LocalDateTime.now().getYear();
        List<YearlySubscribePaymentDto> yearlySubscribePayment = new ArrayList<>();

        for(int i = now; i >= now-4 ; i--) {
            LocalDate beginYear = LocalDate.of(i, 1, 1);
            LocalDate endYear = LocalDate.of(i,12,31);
            Span span = new Span(beginYear, endYear);
            Long totalPayAmount = statisticalRepository.selectYearTotalPayAmount(span);
            yearlySubscribePayment.add(new YearlySubscribePaymentDto(String.valueOf(i), totalPayAmount));
        }
        return yearlySubscribePayment;
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
