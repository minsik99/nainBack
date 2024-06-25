package io.paioneer.nain.report.jpa.repository;

import io.paioneer.nain.report.jpa.entity.RcommunityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CommunityReportRepository extends JpaRepository<RcommunityEntity, Long>, CommunityReportRepositoryCustom {

}
