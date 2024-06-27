package io.paioneer.nain.member.jpa.repository;

import io.paioneer.nain.member.jpa.entity.MemberEntity;

import java.util.Optional;

public interface MemberRepositoryCustom {
    long emailCount(String memberEmail);
}
