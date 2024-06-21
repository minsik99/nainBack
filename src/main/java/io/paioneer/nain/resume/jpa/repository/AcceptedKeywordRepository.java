package io.paioneer.nain.resume.jpa.repository;

import io.paioneer.nain.resume.jpa.entity.AcceptedKeywordEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AcceptedKeywordRepository extends JpaRepository<AcceptedKeywordEntity, Long> {
    List<AcceptedKeywordEntity> findByJobCategory(String jobCategory);
}

