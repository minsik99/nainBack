package io.paioneer.nain.report.jpa.repository;

import io.paioneer.nain.report.jpa.entity.RcommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RcommentRepository extends JpaRepository<RcommentEntity, Long> {
}
