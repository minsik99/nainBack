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

    public int selecttotalPayAmount(Span span) {
        double totalPayAmount = statisticalRepository.totalAmountBetweenDates(span);
        return (int)totalPayAmount;
    }
}
