package io.paioneer.nain.manager.controller;

import io.paioneer.nain.manager.model.dto.UserListDto;
import io.paioneer.nain.manager.model.service.UserManagerService;
import io.paioneer.nain.member.jpa.entity.MemberEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/usermanager")
public class UserManagerController {
    private final UserManagerService userManagerService;

    //유저 조회
    @GetMapping("/userlist")
    public ResponseEntity<List<UserListDto>> userList() {
        List<UserListDto> userListDto = userManagerService.userList();
        return ResponseEntity.status(HttpStatus.OK).body(userListDto);
    }

    //구독처리
    @PutMapping("/updatesubscribe")
    public ResponseEntity<MemberEntity> updateSubscribe(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        MemberEntity updateSubscribe = userManagerService.updateSubscribe(email);
        return ResponseEntity.status(HttpStatus.OK).body(updateSubscribe);
    }

    //구독제거
    @PutMapping("/removesubscribe")
    public ResponseEntity<MemberEntity> removeSubscribe(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        MemberEntity updateSubscribe = userManagerService.removeSubscribe(email);
        return ResponseEntity.status(HttpStatus.OK).body(updateSubscribe);
    }

}
