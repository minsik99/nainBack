package io.paioneer.nain.member.jpa.repository;

import io.paioneer.nain.member.jpa.entity.MemberEntity;
import io.paioneer.nain.member.model.input.InputMember;
import io.paioneer.nain.member.model.output.CustomMemberDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

// 스프링 컨테이너에 의해 관리되는 서비스 컴포넌트로 선언합니다.
@Service
@Slf4j
public class CustomMemberDetailsService implements UserDetailsService {

    // 사용자 정보를 조회하기 위한 MemberRepository 인터페이스입니다.
    private final MemberRepository  memberRepository;
    // 비밀번호를 암호화하기 위한 BCryptPasswordEncoder 입니다.
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    // 생성자를 통해 MemberRepository와 BcryptPasswordEncoder의 인스턴스를 주입받습니다.
    public CustomMemberDetailsService(MemberRepository memberRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.memberRepository = memberRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    // MemberDetailService 인터페이스의 메서드를 구현합니다. 사용자 이름을 기반으로 사용자 정보를 로드합니다.
    @Override
    public UserDetails loadUserByUsername(String username){
        // 사용자 인증을 위한 사용자 검증 로직을 실행합니다.
        MemberEntity memberData = validateMember(new InputMember(username));
        // CustomUserDetails 객체를 생성하여 반환합니다. 이 객체는 Spring Security에 의해 사용자 인증 과정에서 사용됩니다.
        return new CustomMemberDetails(memberData);
    }

    // 사용자의 유효성을 검증하는 메서드입니다.
    private MemberEntity validateMember(InputMember inputMember) {
        // 주어진 이메일로 사용자를 조회합니다. 사용자가 존재하지 않을 경우 UsernameNotFoundException 예외를 발생시킵니다.\
        MemberEntity memberEntity = memberRepository.findByMemberEmail(inputMember.getMemberEmail())
                .orElseThrow(() -> new UsernameNotFoundException("존재하지 않는 이메일입니다." + inputMember.getMemberEmail()));
        // 사용자 계정이 삭제도니 경우 UsernameNotFoundException 예외를 발생시킵니다.
        return memberEntity;
    }

}
