package io.paioneer.nain.statistical.model.dto;

import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class YearlySubscribePaymentDto {
    private String year;
    private long amount;
}
