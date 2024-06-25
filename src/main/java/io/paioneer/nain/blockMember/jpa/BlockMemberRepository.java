package io.paioneer.nain.blockMember.jpa;

import io.paioneer.nain.blockMember.entity.BlockMemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlockMemberRepository extends JpaRepository<BlockMemberEntity, String>, BlockMemberRepositoryCustom {
}
