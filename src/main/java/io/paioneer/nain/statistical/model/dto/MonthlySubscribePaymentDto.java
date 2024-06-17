package io.paioneer.nain.statistical.model.dto;

import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MonthlySubscribePaymentDto {
    private String month;
    private long amount;
}
