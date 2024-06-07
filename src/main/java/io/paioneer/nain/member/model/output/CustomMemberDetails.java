package io.paioneer.nain.member.model.output;


import io.paioneer.nain.member.jpa.entity.MemberEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

// Spring Security의 UserDetails 인터페이스를 구현한 CustomUserDetails 클래스 입니다.
public class CustomMemberDetails implements UserDetails {

    private final MemberEntity memberEntity; // 사용자 정보를 담고 있는 Member 엔티티의 인스턴스입니다.

    // 클래스 생성자에서 Member 엔티티의 인스턴스를 받아 멤버 변수에 할당합니다.
    public CustomMemberDetails(MemberEntity memberEntity) {
        this.memberEntity = memberEntity;
    }

    // 사용자의 권한 목록을 반환하는 메서드입니다.
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();

        // 사용자의 admin 값에 따라 ROLE_ADMIN 또는 ROLE_MEMBER 권한을 부여합니다.
        if(this.memberEntity.getAdmin()){
            authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        }else {
            authorities.add(new SimpleGrantedAuthority("ROLE_MEMBER"));
        }
        return authorities;
    }

    // 사용자의 비밀번호를 반환합니다.
    @Override
    public String getPassword() {
        return memberEntity.getMemberPwd();
    }

    // 사용자의 이메일(사용자명)을 반환합니다.
    @Override
    public String getUsername() {
        return memberEntity.getMemberEmail();
    }

    // 계정이 만료되었는지를 반환합니다. 여기서는 만료되지 않았다고 가정합니다.
    @Override
    public boolean isAccountNonExpired(){
        return true;
    }

    // 계정이 잠겨있지 않은지를 반환합니다.
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    // 사용자의 크리덴셜(비밀번호 등) 이 만료되지 않았는지를 반환합니다.
    @Override
    public boolean isCredentialsNonExpired(){
        return true;
    }

    // 사용자 계정이 활성화(사용 가능) 상태인지를 반환합니다.
    @Override
    public boolean isEnabled(){
        return true;
    }
}
