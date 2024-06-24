package io.paioneer.nain.manager.model.service;

import io.paioneer.nain.manager.jpa.repository.UserManagerRepository;
import io.paioneer.nain.manager.model.dto.UserListDto;
import io.paioneer.nain.member.jpa.entity.MemberEntity;
import io.paioneer.nain.member.jpa.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Transactional
@Service
@Slf4j
@RequiredArgsConstructor
public class UserManagerService {

    private final UserManagerRepository userManagerRepository;
    private final MemberRepository memberRepository;

    public List<UserListDto> userList() {
        return userManagerRepository.userList();
    }

    public MemberEntity updateSubscribe(String email) {
        MemberEntity memberEntity = memberRepository.findByMemberEmail(email).orElseThrow(() -> new IllegalArgumentException("해당 이메일을 가진 사용자를 찾을 수 없습니다: " + email));
        memberEntity.setSubscribeYN("Y");
        return memberRepository.save(memberEntity);
    }

    public MemberEntity removeSubscribe(String email) {
        MemberEntity memberEntity = memberRepository.findByMemberEmail(email).orElseThrow(() -> new IllegalArgumentException("해당 이메일을 가진 사용자를 찾을 수 없습니다: " + email));
        memberEntity.setSubscribeYN("N");
        return memberRepository.save(memberEntity);
    }
}
