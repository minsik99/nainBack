package io.paioneer.nain.member.controller;


import io.paioneer.nain.member.jpa.entity.MemberEntity;
import io.paioneer.nain.member.jpa.repository.MemberRepository;
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

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@Slf4j
public class MemberController {

    private final MemberService memberService;
    private final JWTUtil jWTUtil;
    private final MemberRepository memberRepository;

    @Autowired
    public MemberController(final MemberService memberService, JWTUtil jWTUtil, MemberRepository memberRepository) {
        this.memberService = memberService;
        this.jWTUtil = jWTUtil;
        this.memberRepository = memberRepository;
    }

    @PostMapping("/member/signup")
    public ResponseEntity<?> signUpMember(@RequestBody MemberEntity memberEntity){
        if(memberService.checkEmail(memberEntity.getMemberEmail())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Email already exists");
        }
        MemberEntity newMember = memberService.signUpMember(memberEntity);
        return ResponseEntity.ok(newMember);
    }

    @PostMapping("/member/checkemail")
    public ResponseEntity<?> checkEmail(@RequestBody String memberEmail){
        log.info(memberEmail);
        String msg;

        long count = memberService.emailCount(memberEmail);
        log.info("email count : " + count);

        if(count > 0){
            log.info("있다.");
            msg = "Email already exiss";
            return ResponseEntity.status(HttpStatus.CONFLICT).body(msg);
        }else{
            log.info("없다.");
            msg = "Valid email";
            return ResponseEntity.status(HttpStatus.OK).body(msg);
        }
    }

    @PostMapping ("/myinfo/user")
    public ResponseEntity<MemberDto> selectMemberById(@RequestParam(name = "memberNo") Long memberNo){
        //회원서비스에서 회원 번호로 회원 정보를 조회
        log.info(memberNo.toString());
        MemberDto memberDto = memberService.findById(memberNo);
        log.info(memberDto.toString());
        // 조회된 회원정보를 반환
        return new ResponseEntity<>(memberDto, HttpStatus.OK);
    }

    //내 정보 수정 -------------------------------------------------------------
    @PutMapping("/updateMyinfo")
    public ResponseEntity<?> updateMemberInfo(@RequestBody MemberDto memberDto){
        memberService.updateMemberInfo(memberDto);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }







}
