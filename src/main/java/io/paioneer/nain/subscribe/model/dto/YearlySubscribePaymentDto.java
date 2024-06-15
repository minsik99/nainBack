package io.paioneer.nain.subscribe.model.dto;

import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class YearlySubscribePaymentDto {
    private String year;
    private long totalAmount;
}
