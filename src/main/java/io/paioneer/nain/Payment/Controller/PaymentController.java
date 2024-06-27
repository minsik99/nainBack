package io.paioneer.nain.Payment.Controller;

import org.apache.tomcat.util.json.JSONParser;
import org.apache.tomcat.util.json.ParseException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Controller
public class PaymentController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping(value = "/confirm")
    public ResponseEntity<JSONObject> confirmPayment(@RequestBody String jsonBody) {
        try {
            // JSONParser 객체를 StringReader와 함께 초기화합니다.
            JSONParser parser = new JSONParser(new StringReader(jsonBody));
            JSONObject requestData = (JSONObject) parser.parse();  // 인자 없이 parse 메소드를 호출합니다.
            String paymentKey = requestData.getString("paymentKey");
            String orderId = requestData.getString("orderId");
            String amount = requestData.getString("amount");

            JSONObject obj = new JSONObject();
            obj.put("orderId", orderId);
            obj.put("amount", amount);
            obj.put("paymentKey", paymentKey);

            String widgetSecretKey = "test_sk_4yKeq5bgrpoAPXzMo46XVGX0lzW6";
            Base64.Encoder encoder = Base64.getEncoder();
            byte[] encodedBytes = encoder.encode((widgetSecretKey + ":").getBytes(StandardCharsets.UTF_8));
            String authorizations = "Basic " + new String(encodedBytes);

            URL url = new URL("https://api.tosspayments.com/v1/payments/confirm");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("Authorization", authorizations);
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);

            try (OutputStream outputStream = connection.getOutputStream()) {
                outputStream.write(obj.toString().getBytes(StandardCharsets.UTF_8));
            }

            int code = connection.getResponseCode();
            try (InputStream responseStream = (code == 200) ? connection.getInputStream() : connection.getErrorStream();
                 Reader responseReader = new InputStreamReader(responseStream, StandardCharsets.UTF_8)) {
                // 응답 데이터를 다시 파싱합니다.
                JSONParser responseParser = new JSONParser(responseReader);
                JSONObject responseJson = (JSONObject) responseParser.parse();  // 응답을 파싱합니다.
                return ResponseEntity.status(code).body(responseJson);
            }
        } catch (IOException | ParseException e) {
            logger.error("Error processing payment confirmation", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}