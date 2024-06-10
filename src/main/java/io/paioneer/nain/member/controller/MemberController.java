package io.paioneer.nain.member.controller;


import io.paioneer.nain.member.jpa.entity.MemberEntity;
import io.paioneer.nain.member.model.dto.MemberDto;
import io.paioneer.nain.member.model.input.InputMember;
import io.paioneer.nain.member.model.service.MemberService;
import io.paioneer.nain.security.jwt.util.JWTUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@Slf4j
public class MemberController {

    private final MemberService memberService;
    private final JWTUtil jWTUtil;

    @Autowired
    public MemberController(final MemberService memberService, JWTUtil jWTUtil) {this.memberService = memberService;
        this.jWTUtil = jWTUtil;
    }

    @PostMapping("/member")
    public ResponseEntity<?> signUpMember(@RequestBody InputMember member){
        MemberEntity newMember = memberService.signUpMember(member);
        return ResponseEntity.ok(newMember);
    }

    @GetMapping("/mypage")
    public ResponseEntity<MemberDto> selectMemberById(HttpServletRequest request){
        String token = request.getHeader("Authorization").substring("Bearer ".length());
        Long memberNo = jWTUtil.getMemberNoFromToken(token);
        return new ResponseEntity<>(memberService.findById(memberNo), HttpStatus.OK);
    }

}
