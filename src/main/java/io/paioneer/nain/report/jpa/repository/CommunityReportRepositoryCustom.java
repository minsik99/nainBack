package io.paioneer.nain.report.jpa.repository;

import io.paioneer.nain.report.model.dto.CommunityReportCountDto;
import io.paioneer.nain.report.model.dto.CommunityReportDto;

import java.util.List;

public interface CommunityReportRepositoryCustom {
    List<CommunityReportCountDto> getCommunityReportCount();

    List<CommunityReportDto> getCommunityReport();
}
