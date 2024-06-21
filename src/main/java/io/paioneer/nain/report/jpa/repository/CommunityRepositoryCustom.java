package io.paioneer.nain.report.jpa.repository;

import io.paioneer.nain.report.model.dto.CommunityReportDto;

import java.util.List;

public interface CommunityRepositoryCustom {
    List<CommunityReportDto> getCommunityReport();
}
