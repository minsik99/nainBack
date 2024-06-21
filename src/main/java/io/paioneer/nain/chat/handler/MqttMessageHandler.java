package io.paioneer.nain.chat.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.paioneer.nain.chat.model.dto.MessageDto;
import io.paioneer.nain.chat.model.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;
import org.springframework.stereotype.Component;

@Component
public class MqttMessageHandler implements MessageHandler {

    @Autowired
    private MessageService messageService;

    @Override
    public void handleMessage(Message<?> message) throws MessagingException {
        String payload = (String) message.getPayload();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            MessageDto messageDto = objectMapper.readValue(payload, MessageDto.class);
            messageService.saveMessage(messageDto);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
