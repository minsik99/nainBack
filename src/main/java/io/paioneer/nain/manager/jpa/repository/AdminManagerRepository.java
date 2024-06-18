package io.paioneer.nain.manager.jpa.repository;

import io.paioneer.nain.member.jpa.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminManagerRepository extends JpaRepository<MemberEntity, String>, AdminManagerRepositoryCustom {
}

