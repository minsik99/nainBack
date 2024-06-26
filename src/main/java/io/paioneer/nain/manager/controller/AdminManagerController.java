package io.paioneer.nain.manager.controller;

import io.paioneer.nain.manager.model.dto.AdminListDto;
import io.paioneer.nain.manager.model.dto.UserListDto;
import io.paioneer.nain.manager.model.service.AdminManagerService;
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
@RequestMapping("/adminmanager")
public class AdminManagerController {

    private final AdminManagerService adminManagerService;

    //관리자 조회
    @GetMapping("/adminlist")
    public ResponseEntity<List<AdminListDto>> adminList() {
        List<AdminListDto> adminListDto = adminManagerService.adminList();
        return ResponseEntity.status(HttpStatus.OK).body(adminListDto);
    }

    //관리자 추가
    @PutMapping("/updateadmin")
    public ResponseEntity<MemberEntity> updateAdminStatus(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        MemberEntity updatedMember = adminManagerService.updateAdminStatus(email);
        return ResponseEntity.status(HttpStatus.OK).body(updatedMember);
    }

    //관리자 제거
    @PutMapping("/removeadmin")
    public ResponseEntity<List<MemberEntity>> removeAdminStatus(@RequestBody List<String> admin) {
        List<MemberEntity> updatedMembers = adminManagerService.removeAdminStatus(admin);
        return ResponseEntity.ok(updatedMembers);
    }
}
