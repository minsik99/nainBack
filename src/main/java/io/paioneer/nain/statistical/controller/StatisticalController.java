package io.paioneer.nain.statistical.controller;

import io.paioneer.nain.statistical.model.service.StatisticalService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.Year;
import java.time.format.DateTimeParseException;

@RestController
@RequestMapping("/Staticstical")

public class StatisticalController {
    private final StatisticalService statisticalService;

    public StatisticalController(StatisticalService statisticalService) {
        this.statisticalService = statisticalService;
    }

    @GetMapping("/")
    public ResponseEntity<Integer> yearPaymentCount(@RequestParam("year") String year) {
        try {
            Year parsedYear = Year.parse(year);
            int result = statisticalService.selectYearpaymentCount(parsedYear.getValue());
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (DateTimeParseException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
