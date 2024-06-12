package io.paioneer.nain.security.repository;

import io.paioneer.nain.security.model.entity.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface RefreshRepository extends JpaRepository<RefreshToken, UUID> {
    Optional<RefreshToken> findByTokenValue(String tokenValue);

    Boolean existsByTokenValue(String refresh);

    void deleteByTokenValue(String refresh);

    @Query("SELECT r FROM RefreshToken r WHERE r.memberEntity.memberNo = :member_No")
    Optional<RefreshToken> findByMemberNo(@Param("memberNo") Long memberNo);

}
