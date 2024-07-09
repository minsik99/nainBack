package io.paioneer.nain.Payment.Controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.paioneer.nain.Payment.model.service.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/payment")
@CrossOrigin
public class PaymentController {

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final PaymentService paymentService;

    @PostMapping("/confirm")
    public ResponseEntity<JsonNode> confirmPayment(@RequestBody String jsonBody) {
        try {
            JsonNode requestData = objectMapper.readTree(jsonBody);
            int amount = requestData.get("amount").asInt();
            Long memberNo = requestData.get("memberNo").asLong();

            // 여기서 결제 확인 로직 대신 DB 저장 로직으로 변경
            paymentService.processSubscription(memberNo, amount);
            log.info("Subscription processed successfully for memberNo: {}", memberNo);

            // 성공 응답 반환
            JsonNode responseBody = objectMapper.createObjectNode()
                    .put("success", true)
                    .put("message", "Payment confirmed and subscription processed successfully");

            return ResponseEntity.ok(responseBody);
        } catch (Exception e) {
            log.error("Error processing payment confirmation", e);

            JsonNode errorResponse = objectMapper.createObjectNode()
                    .put("success", false)
                    .put("error", "Error processing payment confirmation");

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }
}
