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

    @PostMapping("/member")
    public ResponseEntity<?> signUpMember(@RequestBody MemberEntity memberEntity){
        MemberEntity newMember = memberService.signUpMember(memberEntity);
        return ResponseEntity.ok(newMember);
    }

    @GetMapping("/mypage")
    public ResponseEntity<MemberDto> selectMemberById(HttpServletRequest request){
        String token = request.getHeader("Authorization").substring("Bearer ".length());
        //JWTUtill 클래스를 사용하여 토큰에서 회원 번호를 추출
        Long memberNo = jWTUtil.getMemberNoFromToken(token);
        //회원서비스에서 회원 번호로 회원 정보를 조회
        MemberDto memberDto = memberService.findById(memberNo);
        // 조회된 회원정보를 반환
        return new ResponseEntity<>(memberDto, HttpStatus.OK);
    }




}
