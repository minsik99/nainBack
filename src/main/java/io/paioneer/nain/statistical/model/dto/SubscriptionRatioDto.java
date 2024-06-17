package io.paioneer.nain.statistical.model.dto;

import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SubscriptionRatioDto {
    private int memberCount;
    private int subscriptionCount;
}
