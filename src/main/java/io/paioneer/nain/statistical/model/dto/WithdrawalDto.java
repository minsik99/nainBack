package io.paioneer.nain.statistical.model.dto;

import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class WithdrawalDto {
    private String Date;
    private int Withdrawal;
}
