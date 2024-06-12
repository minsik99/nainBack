package io.paioneer.nain.member.model.service;

import io.paioneer.nain.member.jpa.entity.MemberEntity;
import io.paioneer.nain.member.jpa.repository.MemberRepository;
import io.paioneer.nain.member.model.dto.MemberDto;
import io.paioneer.nain.member.model.input.InputMember;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
import java.util.UUID;


@Service
public class MemberService {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final MemberRepository memberRepository;

    public MemberService(BCryptPasswordEncoder bCryptPasswordEncoder, MemberRepository memberRepository) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.memberRepository = memberRepository;
    }

    @Transactional
    public MemberEntity signUpMember(InputMember inputMember) {
        memberRepository.findByMemberEmail(inputMember.getMemberEmail())
                .ifPresent(u -> {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "이메일이 이미 사용중입니다.");
                });
        return memberRepository.save(createMember(inputMember));
    }

    public MemberEntity createMember(InputMember member) {
        String encodedPassword = bCryptPasswordEncoder.encode(member.getMemberPwd());
        return MemberEntity.builder()

                .memberEmail(member.getMemberEmail())
                .memberPwd(encodedPassword)
                .admin(false)
                .build();
    }

    @Transactional
    public Optional<MemberEntity> findByMemberEmail(String username){
        return memberRepository.findByMemberEmail(username);
    }



    public MemberDto findById(Long memberNo) {
        return memberRepository.findById(memberNo).get().toDto();
    }
}








