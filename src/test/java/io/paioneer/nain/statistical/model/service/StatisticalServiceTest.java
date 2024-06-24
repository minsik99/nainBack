package io.paioneer.nain.statistical.model.service;

import io.paioneer.nain.statistical.model.dto.MonthlySubscribePaymentDto;
import io.paioneer.nain.statistical.model.dto.NewSubscribeDto;
import io.paioneer.nain.statistical.model.dto.YearlySubscribePaymentDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class StatisticalServiceTest {
    @Autowired
    private StatisticalService service;



    //연매출 테스트코드
    @Test
    public void test1() {
        List<YearlySubscribePaymentDto> dto = service.selectYearlyTotalPayAmount();
        System.out.println(dto);
    }

    //월매출 테스트코드
    @Test
    public void test2() {
        List<MonthlySubscribePaymentDto> dto = service.selectMonthlyTotalPayAmount();
        System.out.println(dto);
    }

    //기간별 신규구독자 테스트코드
    @Test
    public void test3() {
        List<NewSubscribeDto> dto = service.selectNewSubscribeDto();
        System.out.println(dto);
    }
}