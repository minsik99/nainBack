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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Date;
import java.util.Map;


@RestController
@RequestMapping("/api/auth")
@Slf4j
public class MemberController {

    private final MemberService memberService;
    private final JWTUtil jWTUtil;
    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public MemberController(final MemberService memberService, JWTUtil jWTUtil, MemberRepository memberRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.memberService = memberService;
        this.jWTUtil = jWTUtil;
        this.memberRepository = memberRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
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
    public ResponseEntity<?> checkEmail(@RequestBody String memberEmail) {
        try {
            String decodedEmail = URLDecoder.decode(memberEmail, "UTF-8");

            log.info("Decoded email: " + decodedEmail);

            long count = memberService.emailCount(decodedEmail.substring(0, decodedEmail.length() - 1));
            log.info("email count : " + count);

            String msg;

            if (count > 0) {
                log.info("있다.");
                msg = "Email already exists";
                return ResponseEntity.status(HttpStatus.OK).body(msg);
            } else {
                log.info("없다.");
                msg = "Valid email";
                return ResponseEntity.status(HttpStatus.OK).body(msg);
            }
        } catch (UnsupportedEncodingException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error decoding email");
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

    //내 정보 페이지 확인(이메일)
    @PostMapping("/member/myinfoLogin")
    public ResponseEntity<String> selectMemberByEmail(@RequestParam(name = "memberNo") Long memberNo){
        log.info(memberNo.toString());
        MemberDto memberDto = memberService.findById(memberNo);
        String memberEmail = memberDto.getMemberEmail();
        log.info(memberDto.toString());
        return new ResponseEntity<>(memberEmail, HttpStatus.OK);
    }


    //내 정보 페이지 입장 전 정보확인(패스워드)
    @PostMapping("/member/myinfo/{memberNo}")
    public ResponseEntity<?> selectmyinfoLogin(@PathVariable Long memberNo, @RequestBody String memberPwd) {
        log.info("memberPwd" + memberPwd);
        log.info("memberNo:" + memberNo.toString());
        MemberDto memberDto = memberService.findById(memberNo);

        String msg;
        boolean result = bCryptPasswordEncoder.matches(memberPwd, memberDto.getMemberPwd());

        if (result){
            log.info("확인 코드" + memberPwd);
            msg = "Success";
            return ResponseEntity.status(HttpStatus.OK).body(msg);
        }else{
            msg = "Invalid password";
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(msg);
        }
    }




    //내 정보 수정 -------------------------------------------------------------
    @PutMapping("/updateMyinfo/{memberNo}")
    public ResponseEntity<Void> updateMemberInfo(@PathVariable Long memberNo, @RequestBody MemberDto memberDto){
        log.info("Received update request: " + memberDto);

        memberService.updateMemberInfo(memberNo, memberDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //회원 탈퇴 ------------------------------------------------------------------------------------
    @PutMapping("/deletemember/{memberNo}")
    public ResponseEntity<Void> deleteMember(@PathVariable Long memberNo) {
        log.info("Received delete request: ");

        memberService.deleteMember(memberNo);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
