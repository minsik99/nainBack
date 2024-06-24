package io.paioneer.nain.statistical.model.service;


import io.paioneer.nain.common.SpanTime;
import io.paioneer.nain.member.model.dto.MemberDto;
import io.paioneer.nain.statistical.jpa.repository.StatisticalRepository;
import io.paioneer.nain.statistical.model.dto.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import io.paioneer.nain.common.Span;
import org.springframework.transaction.annotation.Transactional;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
@Slf4j
@RequiredArgsConstructor
public class StatisticalService {

    private final StatisticalRepository statisticalRepository;

    public List<YearlySubscribePaymentDto> selectYearlyTotalPayAmount() {
        int now = LocalDateTime.now().getYear();
        List<YearlySubscribePaymentDto> yearlySubscribePayment = new ArrayList<>();

        for(int i = now; i >= now-4 ; i--) {
            LocalDate beginYear = LocalDate.of(i, 1, 1);
            LocalDate endYear = LocalDate.of(i,12,31);
            Span span = new Span(beginYear, endYear);
            Long yearlyTotalPayAmount = statisticalRepository.selectYearlyTotalPayAmount(span);
            yearlySubscribePayment.add(new YearlySubscribePaymentDto(String.valueOf(i), yearlyTotalPayAmount));
        }
        return yearlySubscribePayment;
    }

    public List<MonthlySubscribePaymentDto> selectMonthlyTotalPayAmount() {
        int now = LocalDate.now().getMonthValue();
        List<MonthlySubscribePaymentDto> monthlySubscribePayment = new ArrayList<>();

        for(int i = now; i >= now-4 ; i--) {
            int year = Year.now().getValue();
            int month = now - (now - i);

            if (month <= 0) { // 이전 해로 넘어가는 경우를 처리
                year -= 1;
                month += 12;
            }

            YearMonth yearMonth = YearMonth.of(year, month);
            LocalDate beginMonth = yearMonth.atDay(1); // 해당 월의 1일
            LocalDate endMonth = yearMonth.atEndOfMonth(); // 해당 월의 마지막 날
            Span span = new Span(beginMonth, endMonth);
            Long MonthlyTotalPayment = statisticalRepository.selectMonthlyTotalPayAmount(span);
            monthlySubscribePayment.add(new MonthlySubscribePaymentDto(String.valueOf(i), MonthlyTotalPayment));
        }
        return monthlySubscribePayment;
    }

    public List<SubscriptionRatioDto> selectSubscriptionRatioDto() {

        int MemberCount = statisticalRepository.selectMemberCount();
        int SubscriptionCount = statisticalRepository.selectSubscription();

        List<SubscriptionRatioDto> subscriptionRatioDto = new ArrayList<>();
        subscriptionRatioDto.add(new SubscriptionRatioDto(MemberCount, SubscriptionCount));

        return subscriptionRatioDto;
    }

    public List<NewSubscribeDto> selectNewSubscribeDto() {
        List<NewSubscribeDto> newSubscribeDtoList = new ArrayList<>();
        LocalDate now = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd");

        for (int i = 0; i < 30; i++) {
            LocalDate date = now.minusDays(i);
            LocalDateTime beginDate = date.atStartOfDay();  // 00:00:00
            LocalDateTime endDate = date.atTime(LocalTime.MAX);  // 23:59:59.999999999
            SpanTime span = new SpanTime(beginDate, endDate);
            int newSubscriptions = statisticalRepository.countNewSubscribersInSpan(span);
            String formattedDate = date.format(formatter);
            newSubscribeDtoList.add(new NewSubscribeDto(formattedDate, newSubscriptions));
        }

        return newSubscribeDtoList;
    }

    public List<NewMemberDto> newMemberDto() {
        List<NewMemberDto> newMemberDtoList = new ArrayList<>();
        LocalDate now = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd");

        for (int i = 0; i < 30; i++) {
            LocalDate date = now.minusDays(i);
            LocalDateTime beginDate = date.atStartOfDay();
            LocalDateTime endDate = date.atTime(LocalTime.MAX);
            SpanTime span = new SpanTime(beginDate, endDate);
            int newMembers = statisticalRepository.countNewMembers(span);
            String formattedDate = date.format(formatter);
            newMemberDtoList.add(new NewMemberDto(formattedDate, newMembers));
        }

        return newMemberDtoList;
    }

    public List<WithdrawalDto> withdrawalDto() {
        List<WithdrawalDto> withdrawalDtoList = new ArrayList<>();
        LocalDate now = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd");
        int withdrawalAccumulation = 0; // 변수 초기화

        for (int i = 30; i > 0; i--) {
            LocalDate date = now.minusDays(i);
            LocalDateTime beginDate = date.atStartOfDay();
            LocalDateTime endDate = date.atTime(LocalTime.MAX);
            SpanTime span = new SpanTime(beginDate, endDate);
            int withdrawal = statisticalRepository.withdrawal(span);
            withdrawalAccumulation += withdrawal;
            String formattedDate = date.format(formatter);
            withdrawalDtoList.add(new WithdrawalDto(formattedDate, withdrawalAccumulation));
        }
        return withdrawalDtoList;
    }

}
