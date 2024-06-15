package io.paioneer.nain.statistical.model.service;

import io.paioneer.nain.subscribe.model.dto.YearlySubscribePaymentDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class StatisticalServiceTest {
    @Autowired
    private StatisticalService service;




    @Test
    public void test() {
        List<YearlySubscribePaymentDto> dto = service.selectYearTotalPayAmount();
        System.out.println(dto);
    }
}