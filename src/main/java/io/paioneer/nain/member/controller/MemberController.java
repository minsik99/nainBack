package io.paioneer.nain.member.controller;


import io.paioneer.nain.member.jpa.entity.MemberEntity;
import io.paioneer.nain.member.model.input.InputMember;
import io.paioneer.nain.member.model.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@Slf4j
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(final MemberService memberService) {this.memberService = memberService;}

    @PostMapping("/user")
    public ResponseEntity<?> signUpMember(@RequestBody InputMember member){
        MemberEntity newMember = memberService.signUpMember(member);
        return ResponseEntity.ok(newMember);
    }

}
