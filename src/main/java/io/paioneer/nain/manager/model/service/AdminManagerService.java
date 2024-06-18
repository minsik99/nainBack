package io.paioneer.nain.manager.model.service;

import io.paioneer.nain.manager.jpa.repository.AdminManagerRepository;
import io.paioneer.nain.manager.jpa.repository.UserManagerRepository;
import io.paioneer.nain.manager.model.dto.AdminListDto;
import io.paioneer.nain.member.jpa.entity.MemberEntity;
import io.paioneer.nain.member.jpa.repository.MemberRepository;
import io.paioneer.nain.member.model.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
@Slf4j
@RequiredArgsConstructor
public class AdminManagerService {

    private final AdminManagerRepository adminManagerRepository;
    private final MemberRepository memberRepository;

    public List<AdminListDto> adminList() {
        return adminManagerRepository.adminList();
    }

    public MemberEntity updateAdminStatus(String email) {
        MemberEntity memberEntity = memberRepository.findByMemberEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("해당 이메일을 가진 사용자를 찾을 수 없습니다: " + email));
        memberEntity.setAdmin(true);
        return memberRepository.save(memberEntity);
    }

    public List<MemberEntity> removeAdminStatus(List<String> admin) {
        List<MemberEntity> members = memberRepository.findByMemberEmailIn(admin);
        for (MemberEntity member : members) {
            member.setAdmin(false);
        }
        return memberRepository.saveAll(members);
    }
}
