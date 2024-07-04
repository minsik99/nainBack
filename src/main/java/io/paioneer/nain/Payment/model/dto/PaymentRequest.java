package io.paioneer.nain.Payment.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Component
public class PaymentRequest {
    private String orderId;
    private Long amount;
    private String orderName;
    private String successUrl;
    private String failUrl;
}