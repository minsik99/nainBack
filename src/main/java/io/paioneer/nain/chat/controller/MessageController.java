package io.paioneer.nain.chat.controller;

import io.paioneer.nain.chat.model.dto.ChatRoomDto;
import io.paioneer.nain.chat.model.dto.MessageDto;
import io.paioneer.nain.chat.model.service.MessageService;
import io.paioneer.nain.security.jwt.util.JWTUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/chat")
@RequiredArgsConstructor
public class MessageController {

    private final MessageService messageService;
    private final RestTemplate restTemplate;
    private final JWTUtil jwtUtil;

    @Value("${flask.server.url}")
    private String flaskServerUrl;

    @PostMapping("/rooms/{roomId}/send")
    public ResponseEntity<?> saveMessage(HttpServletRequest request, @PathVariable Long roomId, @RequestBody MessageDto messageDto) {
        String token = request.getHeader("Authorization").substring("Bearer ".length());
        Long memberNo = jwtUtil.getMemberNoFromToken(token);
        messageDto.setMemberNo(memberNo);
        messageDto.setChatRoomNo(roomId);

        log.info("Received message: " + messageDto.toString());

        messageService.saveMessage(messageDto);

        // Flask 서버로 메시지 전달
        restTemplate.postForEntity(flaskServerUrl + "/publish", messageDto, Void.class);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/rooms/{roomId}/messages")
    public ResponseEntity<List<MessageDto>> getAllMessages(@PathVariable Long roomId) {
        List<MessageDto> messages = messageService.getMessagesByRoomId(roomId);
        return ResponseEntity.ok(messages);
    }

    @GetMapping("/rooms")
    public ResponseEntity<List<ChatRoomDto>> getAllRooms() {
        List<ChatRoomDto> rooms = messageService.getAllRooms();
        return ResponseEntity.ok(rooms);
    }
}
