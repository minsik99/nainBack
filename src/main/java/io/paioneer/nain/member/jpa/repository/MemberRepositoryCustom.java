package io.paioneer.nain.member.jpa.repository;


import io.paioneer.nain.member.jpa.entity.MemberEntity;

public interface MemberRepositoryCustom {
    //JPA가 제공하지 않는 기능을 작동시키키 위한 메서드 추가함

    //회원가입시 아이디(이메일) 중복확인용
    MemberEntity findByMemberEmail(String email);

}
