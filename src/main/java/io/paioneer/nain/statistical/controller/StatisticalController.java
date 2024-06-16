package io.paioneer.nain.statistical.controller;

import io.paioneer.nain.common.Span;
import io.paioneer.nain.statistical.model.service.StatisticalService;
import io.paioneer.nain.subscribe.model.dto.YearlySubscribePaymentDto;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.function.Function;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/staticstical")
public class StatisticalController {
    private final StatisticalService statisticalService;

    private ResponseEntity<Long> getStatistical(Function<Span, Long> spanFunction, LocalDate begin, LocalDate end) {
        Span span = new Span(begin, end);
        try {
            long result = spanFunction.apply(span);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    //매출 통계
    @GetMapping("/yeartotalpayamount")
    public ResponseEntity<?> totalPayAmount(){
        List<YearlySubscribePaymentDto> dto = statisticalService.selectYearTotalPayAmount();
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    //신규구독자 통계
    @GetMapping("/newSubscribeCount")
    public ResponseEntity<Long> newSubscribeCount(@RequestParam("begin") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate begin,@RequestParam("end") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end){
        return getStatistical(statisticalService::selectNewSubscribeCount, begin, end);
    }

//    //방문자수 통계
//    @GetMapping("/visitorCount")
//    public ResponseEntity<Long> visitorCount(@RequestParam("begin") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate begin,@RequestParam("end") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end){
//        return getStatistical(statisticalService::selectVisitorCount, begin, end);
//    }

    //가입자 통계
    @GetMapping("/signupCount")
    public ResponseEntity<Long> signupCount(@RequestParam("begin") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate begin,@RequestParam("end") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end){
        return getStatistical(statisticalService::selectSignupCount, begin, end);
    }

    //탈퇴자 통계
    @GetMapping("/withdrawalCount")
    public ResponseEntity<Long> withdrawalCount(@RequestParam("begin") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate begin,@RequestParam("end") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end){
        return getStatistical(statisticalService::selectWithdrawalCount, begin, end);
    }


}
