package io.paioneer.nain.statistical.controller;

import io.paioneer.nain.common.Span;
import io.paioneer.nain.statistical.model.service.StatisticalService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;


@RestController
@RequestMapping("/Staticstical")

public class StatisticalController {
    private final StatisticalService statisticalService;

    public StatisticalController(StatisticalService statisticalService) {
        this.statisticalService = statisticalService;
    }

    //매출 통계
    @GetMapping("/")
    public ResponseEntity<Integer> totalPayAmount(@RequestParam("begin") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate begin,@RequestParam("end") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end) {

        Span span = new Span(begin, end);

        try {
            int result = statisticalService.selecttotalPayAmount(span);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
