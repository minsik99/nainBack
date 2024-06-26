package io.paioneer.nain.subscribe.controller;

import io.paioneer.nain.subscribe.model.service.SubscribeService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;


@RestController
@Slf4j
@RequestMapping("/subscribe")
@RequiredArgsConstructor
public class SubscribeController {

    private final SubscribeService subscribeService;

    @PostMapping("/sign")
    public ResponseEntity<?> SignPayment(HttpServletRequest request) {
        log.info("request");
        URL url = null;
        URLConnection connection = null;
        StringBuilder responseBody = new StringBuilder();
        try {
            url = new URL("https://pay.toss.im/api/v2/payments");
            connection = url.openConnection();
            connection.addRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);
            connection.setDoInput(true);

            JSONObject jsonBody = new JSONObject();
            jsonBody.put("payNo", "1");
            jsonBody.put("payAmount", 10000);
            jsonBody.put("amountTaxFree", 0);
            jsonBody.put("productDesc", "테스트 결제");
            jsonBody.put("apiKey", "test_ck_6bJXmgo28e26vbqqGdA63LAnGKWx");
            jsonBody.put("autoExecute", true);
            jsonBody.put("resultCallback", "http://localhost:9999/subscribe/sign");
            jsonBody.put("callbackVersion", "V2");
            jsonBody.put("retUrl", "http://localhost:3000/subsSuccess?payNo=1");
            jsonBody.put("retCancelUrl", "http://localhost:3000/close");

            BufferedOutputStream bos = new BufferedOutputStream(connection.getOutputStream());

            bos.write(jsonBody.toString().getBytes(StandardCharsets.UTF_8));
            bos.flush();
            bos.close();


            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8));
            String line = null;
            while ((line = br.readLine()) != null) {
                responseBody.append(line);
            }
            br.close();

            return  ResponseEntity.ok(responseBody.toString());
        } catch (Exception e) {
            log.error("Error during payment sign request", e);
            responseBody.append(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseBody.toString());
        }
    }
}