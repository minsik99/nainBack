package io.paioneer.nain.statistical.controller;

import io.paioneer.nain.statistical.model.dto.*;
import io.paioneer.nain.statistical.model.service.StatisticalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/staticstical")
public class StatisticalController {
    private final StatisticalService statisticalService;

    //연매출 통계
    @GetMapping("/payamount/yearly")
    public ResponseEntity<List<YearlySubscribePaymentDto>> yearlyTotalPayAmount(){
        List<YearlySubscribePaymentDto> yearlyPayAmountDto = statisticalService.selectYearlyTotalPayAmount();
        return ResponseEntity.status(HttpStatus.OK).body(yearlyPayAmountDto);
    }

    //월매출 통계
    @GetMapping("/payamount/monthly")
    public ResponseEntity<List<MonthlySubscribePaymentDto>> monthlyTotalPayment(){
        List<MonthlySubscribePaymentDto> monthlyPayAmountDto = statisticalService.selectMonthlyTotalPayAmount();
        return ResponseEntity.status(HttpStatus.OK).body(monthlyPayAmountDto);
    }

    //현재구독자 통계
    @GetMapping("/count/subscriptionratio")
    public ResponseEntity<List<SubscriptionRatioDto>> SubscriptionRatioDto(){
        List<SubscriptionRatioDto> subscriptionRatioDto = statisticalService.selectSubscriptionRatioDto();
        return ResponseEntity.status(HttpStatus.OK).body(subscriptionRatioDto);

    }

    //신규구독자 통계
    @GetMapping("/count/newsubscribe")
    public ResponseEntity<List<NewSubscribeDto>> newSubscribe(){
        List<NewSubscribeDto> newSubscribeDto = statisticalService.selectNewSubscribeDto();
        return ResponseEntity.status(HttpStatus.OK).body(newSubscribeDto);
    }

    // 일 가입자 수
    @GetMapping("/count/newmember")
    public ResponseEntity<List<NewMemberDto>> newMember(){
        List<NewMemberDto> newMemberDto = statisticalService.newMemberDto();
        return ResponseEntity.status(HttpStatus.OK).body(newMemberDto);
    }

    // 일 탈퇴자 수
    @GetMapping("/count/withdrawal")
    public ResponseEntity<List<WithdrawalDto>> withdrawal(){
        List<WithdrawalDto> withdrawalDto = statisticalService.withdrawalDto();
        return ResponseEntity.status(HttpStatus.OK).body(withdrawalDto);
    }
}
